package com.vti;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
@CrossOrigin("*")
public class HelloController {

	@GetMapping
	public String hello(@RequestParam(value = "name") String nameAccount) {
		return "Hello world" + nameAccount;

	}
}
