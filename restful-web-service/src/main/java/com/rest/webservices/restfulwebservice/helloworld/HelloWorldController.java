package com.rest.webservices.restfulwebservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;
	
	public HelloWorldController() {
		//public String helloWorld = new String("Hello World Restful Service");
	}
	
	public String helloWorld () {
		return "Hello World";
	}
	
	@GetMapping( path="/hello-world")
	public String hellowWorld() {
		return helloWorld();
	}
	@GetMapping( path="/hello-world-bean")
	public HelloWorldBean hellowWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}
	@GetMapping( path="/hello-world/{name}")
	public HelloWorldBean hellowWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World Bean, %s",name));
	}
	@GetMapping( path="/hello-world-internationalized")
	public String helloWorldInternationlized(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
