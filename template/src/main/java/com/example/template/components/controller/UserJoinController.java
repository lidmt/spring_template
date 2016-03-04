package com.example.template.components.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.template.dto.user.User;

@Controller
public class UserJoinController {
	
	@RequestMapping(value="/user/join", method=RequestMethod.GET)
	public String joinPage(@ModelAttribute("user") User user) {
		return "user/join";
	}
}
