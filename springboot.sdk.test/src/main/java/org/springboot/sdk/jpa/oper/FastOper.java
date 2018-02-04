package org.springboot.sdk.jpa.oper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public abstract class FastOper implements IFastOper{
	public CriteriaBuilder cb;
	public Root<?> root;
	
	public FastOper(CriteriaBuilder cb,Root<?> root) {
		this.cb = cb;
		this.root = root;
	}
	
	
	
}
