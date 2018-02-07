package org.springboot.sdk.jpa.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springboot.sdk.jpa.model.BaseModel;
import org.springboot.sdk.jpa.model.TimeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IFindBase<T extends BaseModel,ID  extends Serializable> {
public abstract JpaSpecificationExecutor<T> specjpa();
	
	/**
	 * 设置JpaRepository
	 * @return
	 */
	public  JpaRepository<T,ID> jpa();
	
	/**
	 * 自定义查询条件
	 * @param t
	 * @param predicates
	 * @param root
	 * @param query
	 * @param cb
	 */
	public  void where(T t,List<Predicate>  predicates,Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
	
	
	/**
	 * 调整查询结果
	 * @param t
	 */
	public  void setSelect(T t);
	/**
	 * 分页条件查询
	 * @param t
	 * @param pageRequest
	 * @param createTimeEntity
	 * @param updTimeEntity
	 * @return
	 */
	public Page<T> findAll(T t, PageRequest pageRequest,TimeEntity createTimeEntity,TimeEntity updTimeEntity);
	
	/**
	 * 条件查询第一条记录
	 * @param t
	 * @param pageRequest
	 * @param createTimeEntity
	 * @param updTimeEntity
	 * @param other
	 * @return
	 */
	public T find(T t, TimeEntity createTimeEntity,TimeEntity updTimeEntity);
	
	
	
	/**
	 * 批量保存或更新
	 * @param entities
	 * @return
	 */
	public List<T> save(Iterable<T> entities);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public T findOne(ID id);
	
	
	
	/**
	 * 条件查询所有属性
	 * @param t
	 * @param createTimeEntity
	 * @param updTimeEntity
	 * @return
	 */
	public List<T> findAll(T t,TimeEntity createTimeEntity,TimeEntity updTimeEntity);
	
	
	
	/**
	 * 删除
	 * @param t
	 * @return
	 */
	public List<T> delete(T t);
	
	/**
	 * 更新对应字段，val为空则更新所有字段
	 * @param t 需要更新的值
	 * @param id 主键id
	 * @param val 需要更新的值
	 * @return
	 */
	public T updateIncludeValue(T t,ID id,String... val);
	
	/**
	 * 保存或更新：根据是否有主键自动判断
	 * @param t
	 * @return
	 */
	public T save(T t);
}
