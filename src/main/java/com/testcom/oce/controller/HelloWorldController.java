package com.testcom.oce.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path="/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/helloWorldBean")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("Hello World");
	}
	
	@GetMapping(path="/helloWorldI18N")
	public String helloWorldI18N(@RequestHeader(name="Accept-Language", required= false) Locale locale) {
		return messageSource.getMessage("lblGoodMorning", null, locale);
	}
	
	@GetMapping(path="/greetings/{name}")
	public HelloWorld getGreetings(@PathVariable String name) {
		return new HelloWorld("Hello, "+name);
	}
	
}
