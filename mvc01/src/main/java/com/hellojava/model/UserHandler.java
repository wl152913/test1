package com.hellojava.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hellojava.business.IUserService;
import com.hellojava.entity.User;

@Controller
public class UserHandler {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/loginHandler")
	public String loginHandler(User user) {
		
		boolean bool=userService.checkUser(user);
		return bool?"loadAll":"error.jsp";
	}
}
