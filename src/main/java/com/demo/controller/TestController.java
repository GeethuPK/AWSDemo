package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/welcome")
	public String testRestApi() {

		System.out.println("Hello");
		return "Welcome to AWS Delpoyed Appplication";
	}

}
