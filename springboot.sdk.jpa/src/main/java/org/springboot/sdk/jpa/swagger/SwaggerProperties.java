package org.springboot.sdk.jpa.swagger;

import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;

import org.springframework.boot.context.properties.ConfigurationProperties;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 翟永超
 * Create date ：2017/8/7.
 * My blog： http://blog.didispace.com
 */
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**是否开启swagger**/
    private Boolean enabled;

    /**标题**/
    private String title = "test";
    /**描述**/
    private String description = "";
    /**版本**/
    private String version = "";
    /**许可证**/
    private String license = "";
    /**许可证URL**/
    private String licenseUrl = "";
    /**服务条款URL**/
    private String termsOfServiceUrl = "";

    /**忽略的参数类型**/
    private List<Class> ignoredParameterTypes = new ArrayList<>();

    private Contact contact = new Contact();

    /**swagger会解析的包路径**/
    private String basePackage = "";

    /**swagger会解析的url规则**/
    private List<String> basePath = new ArrayList<>();
    /**在basePath基础上需要排除的url规则**/
    private List<String> excludePath = new ArrayList<>();

    /**分组文档**/
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();

    /**host信息**/
    private String host = "";

    /**全局参数配置**/
    private List<GlobalOperationParameter> globalOperationParameters;

    /** 页面功能配置 **/
    private UiConfig uiConfig = new UiConfig();

    /** 是否使用默认预定义的响应消息 ，默认 true **/
    private Boolean applyDefaultResponseMessages = true;

    /** 全局响应消息 **/
    private GlobalResponseMessage globalResponseMessage;


    @Data
    @NoArgsConstructor
    public static class GlobalOperationParameter{
        /**参数名**/
        private String name;

        /**描述信息**/
        private String description;

        /**指定参数类型**/
        private String modelRef;

        /**参数放在哪个地方:header,query,path,body.form**/
        private String parameterType;

        /**参数是否必须传**/
        private String required;

    }

    @Data
    @NoArgsConstructor
    public static class DocketInfo {

        /**标题**/
        private String title = "";
        /**描述**/
        private String description = "";
        /**版本**/
        private String version = "";
        /**许可证**/
        private String license = "";
        /**许可证URL**/
        private String licenseUrl = "";
        /**服务条款URL**/
        private String termsOfServiceUrl = "";

        private Contact contact = new Contact();

        /**swagger会解析的包路径**/
        private String basePackage = "";

        /**swagger会解析的url规则**/
        private List<String> basePath = new ArrayList<>();
        /**在basePath基础上需要排除的url规则**/
        private List<String> excludePath = new ArrayList<>();

        private List<GlobalOperationParameter> globalOperationParameters;

        /**忽略的参数类型**/
        private List<Class> ignoredParameterTypes = new ArrayList<>();

    }

    @Data
    @NoArgsConstructor
    public static class Contact {

        /**联系人**/
        private String name = "";
        /**联系人url**/
        private String url = "";
        /**联系人email**/
        private String email = "";

    }

    @Data
    @NoArgsConstructor
    public static class GlobalResponseMessage {

        /** POST 响应消息体 **/
        List<GlobalResponseMessageBody> post = new ArrayList<>();

        /** GET 响应消息体 **/
        List<GlobalResponseMessageBody> get = new ArrayList<>();

        /** PUT 响应消息体 **/
        List<GlobalResponseMessageBody> put = new ArrayList<>();

        /** PATCH 响应消息体 **/
        List<GlobalResponseMessageBody> patch = new ArrayList<>();

        /** DELETE 响应消息体 **/
        List<GlobalResponseMessageBody> delete = new ArrayList<>();

        /** HEAD 响应消息体 **/
        List<GlobalResponseMessageBody> head = new ArrayList<>();

        /** OPTIONS 响应消息体 **/
        List<GlobalResponseMessageBody> options = new ArrayList<>();

        /** TRACE 响应消息体 **/
        List<GlobalResponseMessageBody> trace = new ArrayList<>();

    }

    @Data
    @NoArgsConstructor
    public static class GlobalResponseMessageBody {

        /** 响应码 **/
        private int code;

        /** 响应消息 **/
        private String message;

        /** 响应体 **/
        private String modelRef;

    }


    @Data
    @NoArgsConstructor
    public static class UiConfig {

        private String validatorUrl;
        private DocExpansion docExpansion = DocExpansion.NONE;    // none | list
//        private String docExpansion = "none";
        private String apiSorter = "alpha";       // alpha
        private ModelRendering defaultModelRendering = ModelRendering.MODEL;   // schema
//        private String defaultModelRendering = "schema";

        /** 是否启用json编辑器 **/
        private Boolean jsonEditor = false;
        /** 是否显示请求头信息 **/
        private Boolean showRequestHeaders = true;
        /** 支持页面提交的请求类型 **/
        private String submitMethods = "get,post,put,delete,patch";
        /** 请求超时时间 **/
        private Long requestTimeout = 10000L;
        
        /**是否启用 deep link **/               
        private Boolean deepLinking = false;
        /**Controls the display of operationId in operations list. The default is false **/
        private Boolean displayOperationId = false ;
        /** The default expansion depth for models (set to -1 completely hide the models)**/
        private Integer defaultModelsExpandDepth = 1;
        /** The default expansion depth for the model on the model-example section **/
        private Integer defaultModelExpandDepth = 1;
        /** Controls the display of the request duration (in milliseconds) for Try-It-Out requests**/
        private Boolean displayRequestDuration = false;
        /**  If set, enables filtering. The top bar will show an edit box that you can use to
   *                                 filter the tagged operations that are shown. Can be Boolean to enable or disable,
   *                                 or a string, in which case filtering will be enabled using that string as the
   *                                 filter expression. Filtering is case sensitive matching the filter expression
   *                                 anywhere inside the tag**/
        private Object filter = false;
        /** If set, limits the number of tagged operations displayed to at most this many. The
   *                                 default is to show all operations**/
        private Integer maxDisplayedTags;
        /**Apply a sort to the operation list of each API. It can be 'alpha' (sort by paths
   *                                 alphanumerically), 'method' (sort by HTTP method) or a function (see
   *                                 Array.prototype.sort() to know how sort function works). Default is the order
   *                                 returned by the server unchanged **/
        private OperationsSorter operationsSorter;
        /** Controls the display of vendor extension (x-) fields and values for Operations,
   *                                 Parameters, and Schema **/
        private Boolean showExtensions;
        /**Apply a sort to the tag list of each API. It can be 'alpha' (sort by paths
   *                                 alphanumerically) or a function (see Array.prototype.sort() to learn how to write a
   *                                 sort function). Two tag name strings are passed to the sorter for each pass.
   *                                 Default is the order determined by Swagger-UI **/
        private TagsSorter tagsSorter;
    }

}


