package org.demo.controller;

import java.util.List;

import org.demo.model.User;
import org.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;



@RestController
public class TestController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="findUser",method=RequestMethod.GET)
	public List<User> findAll() {
		return userService.jpa().findAll();
	}
	
	
	@RequestMapping(value="save",method=RequestMethod.GET)
	public void save() {
		User user = new User();
		user.age =10;
		user.name = "test";
		userService.save(user);
	}
}
