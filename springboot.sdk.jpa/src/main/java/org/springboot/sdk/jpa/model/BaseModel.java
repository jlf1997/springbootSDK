package org.springboot.sdk.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
	
	public BaseModel() {
		
	}
	

	/**
	 * 综合状态码
	 */
	@Column(nullable=true)
	@ApiModelProperty(value = "综合状态码", example = "2")
	protected Long cstate;
	
	/**
	 * 创建时间
	 */
	@CreatedDate	
	@Column(updatable=false)
	@ApiModelProperty(value = "创建时间", example = "2014-01-01 14:22:13")
	protected Date creTime;
	
	/**
	 * 最后更新时间
	 */
	@LastModifiedDate
	@ApiModelProperty(value = "最后更新时间", example = "2014-01-01 14:22:13")
	protected Date  updTime;
	
	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除", example = "1")
	protected Integer deleted  ;
	
	@CreatedBy
	@ApiModelProperty(value = "创建人id", example = "1")
	protected Long createBy;
	
	@LastModifiedBy
	@ApiModelProperty(value = "修改人id", example = "1")
	protected Long modifiedBy;
	
	@Version
	@ApiModelProperty(value = "数据库记录版本号，乐观锁", example = "1")
	protected Long version;



	public Long getCstate() {
		return cstate;
	}

	public void setCstate(Long cstate) {
		this.cstate = cstate;
	}

	public Date getCreTime() { 
		return creTime;
	}

	public void setCreTime(Date creTime) { 
		this.creTime = creTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
	

	
	
	
	
}
