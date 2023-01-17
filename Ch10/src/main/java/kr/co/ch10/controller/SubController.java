package kr.co.ch10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubController {

	@GetMapping("/sub/hello")
	public String hello() {
		
		log.trace("hello trace");
		log.debug("hello debug");
		log.info("hello info");
		log.warn("hello warn");
		log.error("hello error");
		
		return "/sub/hello";
	}
	
	@GetMapping("/sub/welcome")
	public String welcome() {
		
		log.trace("welcome trace");
		log.debug("welcome debug");
		log.info("welcome info");
		log.warn("welcome warn");
		log.error("welcome error");
		
		return "/sub/welcome";
	}
	
	@GetMapping("/sub/greeting")
	public String greeting() {
		
		log.trace("greeting trace");
		log.debug("greeting debug");
		log.info("greeting info");
		log.warn("greeting warn");
		log.error("greeting error");
		
		return "/sub/greeting";
	}
}
