package org.springboot.sdk.jpa.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 翟永超
 * Create date：2017/8/7.
 * My blog： http://blog.didispace.com
 */
@Configuration
@Import({
        Swagger2Configuration.class
})
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

   

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(UiConfiguration.class)
    @ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
    public Docket createRestApi(SwaggerProperties swaggerProperties) {
       ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
       Docket docket =  new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .enable(swaggerProperties.getEnabled())
      .select()
      .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
      .paths(PathSelectors.any())
      .build();
      
      configurableBeanFactory.registerSingleton("defaultDocket", docket);
      return docket;
    }


  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("Spring Boot中使用Swagger2构建RESTful APIs")
              .description("swagger2 demo")
              .termsOfServiceUrl("http://blog.didispace.com/")
              .contact("yx")
              .version("1.0")
              .build();
  }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    private List<Parameter> buildGlobalOperationParametersFromSwaggerProperties(
            List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters) {
        List<Parameter> parameters = Lists.newArrayList();

        if (Objects.isNull(globalOperationParameters)) {
            return parameters;
        }
        for (SwaggerProperties.GlobalOperationParameter globalOperationParameter : globalOperationParameters) {
            parameters.add(new ParameterBuilder()
                    .name(globalOperationParameter.getName())
                    .description(globalOperationParameter.getDescription())
                    .modelRef(new ModelRef(globalOperationParameter.getModelRef()))
                    .parameterType(globalOperationParameter.getParameterType())
                    .required(Boolean.parseBoolean(globalOperationParameter.getRequired()))
                    .build());
        }
        return parameters;
    }

    /**
     * 局部参数按照name覆盖局部参数
     *
     * @param globalOperationParameters
     * @param docketOperationParameters
     * @return
     */
    private List<Parameter> assemblyGlobalOperationParameters(
            List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters,
            List<SwaggerProperties.GlobalOperationParameter> docketOperationParameters) {

        if (Objects.isNull(docketOperationParameters) || docketOperationParameters.isEmpty()) {
            return buildGlobalOperationParametersFromSwaggerProperties(globalOperationParameters);
        }

        Set<String> docketNames = docketOperationParameters.stream()
                .map(SwaggerProperties.GlobalOperationParameter::getName)
                .collect(Collectors.toSet());

        List<SwaggerProperties.GlobalOperationParameter> resultOperationParameters = Lists.newArrayList();

        if (Objects.nonNull(globalOperationParameters)) {
            for (SwaggerProperties.GlobalOperationParameter parameter : globalOperationParameters) {
                if (!docketNames.contains(parameter.getName())) {
                    resultOperationParameters.add(parameter);
                }
            }
        }

        resultOperationParameters.addAll(docketOperationParameters);
        return buildGlobalOperationParametersFromSwaggerProperties(resultOperationParameters);
    }

    /**
     * 设置全局响应消息
     *
     * @param swaggerProperties 支持 POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE
     * @param docketForBuilder
     */
    private void buildGlobalResponseMessage(SwaggerProperties swaggerProperties, Docket docketForBuilder) {

        SwaggerProperties.GlobalResponseMessage globalResponseMessages =
                swaggerProperties.getGlobalResponseMessage();

        // POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE 响应消息体
        List<ResponseMessage> postResponseMessages      = getResponseMessageList(globalResponseMessages.getPost());
        List<ResponseMessage> getResponseMessages       = getResponseMessageList(globalResponseMessages.getGet());
        List<ResponseMessage> putResponseMessages       = getResponseMessageList(globalResponseMessages.getPut());
        List<ResponseMessage> patchResponseMessages     = getResponseMessageList(globalResponseMessages.getPatch());
        List<ResponseMessage> deleteResponseMessages    = getResponseMessageList(globalResponseMessages.getDelete());
        List<ResponseMessage> headResponseMessages      = getResponseMessageList(globalResponseMessages.getHead());
        List<ResponseMessage> optionsResponseMessages   = getResponseMessageList(globalResponseMessages.getOptions());
        List<ResponseMessage> trackResponseMessages     = getResponseMessageList(globalResponseMessages.getTrace());

        docketForBuilder.useDefaultResponseMessages(swaggerProperties.getApplyDefaultResponseMessages())
                .globalResponseMessage(RequestMethod.POST,      postResponseMessages)
                .globalResponseMessage(RequestMethod.GET,       getResponseMessages)
                .globalResponseMessage(RequestMethod.PUT,       putResponseMessages)
                .globalResponseMessage(RequestMethod.PATCH,     patchResponseMessages)
                .globalResponseMessage(RequestMethod.DELETE,    deleteResponseMessages)
                .globalResponseMessage(RequestMethod.HEAD,      headResponseMessages)
                .globalResponseMessage(RequestMethod.OPTIONS,   optionsResponseMessages)
                .globalResponseMessage(RequestMethod.TRACE,     trackResponseMessages);
    }

    /**
     * 获取返回消息体列表
     *
     * @param globalResponseMessageBodyList
     * @return
     */
    private List<ResponseMessage> getResponseMessageList(List<SwaggerProperties.GlobalResponseMessageBody> globalResponseMessageBodyList) {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        for (SwaggerProperties.GlobalResponseMessageBody globalResponseMessageBody : globalResponseMessageBodyList) {
            ResponseMessageBuilder responseMessageBuilder = new ResponseMessageBuilder();
            responseMessageBuilder.code(globalResponseMessageBody.getCode()).message(globalResponseMessageBody.getMessage());

            if (!StringUtils.isEmpty(globalResponseMessageBody.getModelRef())) {
                responseMessageBuilder.responseModel(new ModelRef(globalResponseMessageBody.getModelRef()));
            }
            responseMessages.add(responseMessageBuilder.build());
        }

        return responseMessages;
    }
}
