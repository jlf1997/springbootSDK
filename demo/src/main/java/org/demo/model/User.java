package org.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springboot.sdk.jpa.model.BaseModel;

import com.google.gson.annotations.Expose;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table
@ApiModel(value="User",description="用户信息")
public class User extends BaseModel{

	
	@Id
	@GeneratedValue
	public Long id;
	
	@ApiModelProperty(value = "名称", example = "张三")
	public String name;
	 
	@ApiModelProperty(value = "年龄", example = "20")
	public int age;
	 
	@ApiModelProperty(value = "日期", example = "2013-04-15 12:24:14")
	public Date time;
	
	@Expose(serialize=false)
	@ApiModelProperty(hidden=true)
	public String remark;
}
