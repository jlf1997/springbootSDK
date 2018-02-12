package org.demo.jpa;

import org.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserJpa extends JpaRepository<User,Long>,JpaSpecificationExecutor<User>{

}
