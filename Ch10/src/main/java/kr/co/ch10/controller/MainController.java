package kr.co.ch10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value= {"/", "/index"})
	public String index() {
		
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		
		return "/index";
	}
	
	
}
