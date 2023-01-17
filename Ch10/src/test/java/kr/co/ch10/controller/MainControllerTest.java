package kr.co.ch10.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class MainControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void index() throws Exception {
		mvc.perform(get("/index")).andExpect(status().isOk()).andDo(print());
	}
	
	@BeforeAll
	public static void before() {
		System.out.println("before 실행");
	}
	
	@Test
	public void hello() throws Exception {
		mvc.perform(get("/hello")).andExpect(status().isOk()).andDo(print());
	}
	
	@AfterAll
	public static void after() {
		System.out.println("after 실행");
	}
}
