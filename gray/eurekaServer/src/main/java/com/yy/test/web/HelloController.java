package com.huayun.test.web;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;
}
