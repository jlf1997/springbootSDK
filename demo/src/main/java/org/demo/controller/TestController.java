package org.demo.controller;

import java.util.List;

import org.demo.model.User;
import org.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(description="用户相关操作",tags= {"user"})
@RequestMapping("user")
@RestController
public class TestController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "查询所有用户", notes = "查询所有，不分页")
	@RequestMapping(value="findUser",method=RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll(null,null,null);
	}
	
	@ApiOperation(value = "新增用户", notes = "")
	@RequestMapping(value="save",method=RequestMethod.GET)
	public void save() {
		User user = new User();
		user.age =10;
		user.name = "name";
		userService.save(user);
	}
	
	@RequestMapping(value="delete/{userid}",method=RequestMethod.DELETE)
	public void delUser(@PathVariable Long userid) {
		User t = new User();
		t.id = userid;
		userService.delete(t);
	}
}
