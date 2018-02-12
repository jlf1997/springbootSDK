package org.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springboot.sdk.jpa.model.BaseModel;

@Entity
@Table
public class User extends BaseModel{

	
	@Id
	@GeneratedValue
	public Long id;
	
	public String name;
	
	public int age;
	
	public Date time;
}
