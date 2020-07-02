package com.huayun.test.web;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class HelloController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	Environment env;

	@RequestMapping("/testService")
	public String get(){
		return "hellow serviceA";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> testGet(@RequestParam(value = "version", required = false) String version) {
		return ImmutableMap.of("test", "success.", "version", StringUtils.defaultIfEmpty(version, ""), "serverPort", env.getProperty("server.port"));
	}
}
