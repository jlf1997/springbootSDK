package org.demo.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.demo.jpa.UserJpa;
import org.demo.model.User;
import org.springboot.sdk.jpa.service.FindBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService extends FindBase<User, Long>{

	@Autowired	
	private UserJpa userJpa;
	
	
	@Override
	public JpaRepository<User, Long> jpa() {
		// TODO Auto-generated method stub
		return userJpa;
	}

	@Override
	public void setSelect(User arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JpaSpecificationExecutor<User> specjpa() {
		// TODO Auto-generated method stub
		return userJpa;
	}

	@Override
	public void where(User arg0, List<Predicate> arg1, Root<User> arg2, CriteriaQuery<?> arg3, CriteriaBuilder arg4) {
		// TODO Auto-generated method stub
		
	}

}
