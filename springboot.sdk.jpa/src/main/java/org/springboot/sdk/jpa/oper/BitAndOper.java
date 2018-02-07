package org.springboot.sdk.jpa.oper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BitAndOper<T> extends FastOper{
	



	public BitAndOper(CriteriaBuilder cb, Root<?> root) {
		super(cb, root);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param val1 数据库中字段
	 * @param val2 运算值
	 * @return
	 */
	public Expression<Long> bitAnd(String val1,Long val2) {
		Expression<Long> ex = cb.function("bitand", Long.class, root.get("orderState"),cb.literal(val2));
		return ex;
	}
	
	/**
	 * 
	 * @param val1 数据库中字段
	 * @param val2 运算值
	 * @return
	 */
	public Expression<Integer> bitAnd(String val1,Integer val2) {
		Expression<Integer> ex = cb.function("bitand", Integer.class, root.get("orderState"),cb.literal(val2));
		return ex;
	}
	
	
	public Predicate bitAndEqualZero(String val1,Long val2) {
		Expression<Long> ex = bitAnd(val1,val2);
		return cb.greaterThan(ex,  cb.literal(0L));
	}
	
	
	
}
