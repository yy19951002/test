package com.huayun.test.web;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("/testService")
	public String get(){
		return "hellow serviceA";
	}
}
