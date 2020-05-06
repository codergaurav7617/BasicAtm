package com.ril.jpm.test.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/control")
public class Controller1 {
	@RequestMapping("/home")
	public String home(HttpServletRequest httpServletRequest) {
		System.out.println(httpServletRequest.getRemoteUser());
		System.out.println(httpServletRequest.getRemoteUser());
		System.out.println(httpServletRequest.getRemoteUser());
		System.out.println(httpServletRequest.getRemoteUser());
		return "home";
	}
}
