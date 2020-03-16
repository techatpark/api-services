package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}