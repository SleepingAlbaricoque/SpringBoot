package kr.co.ch09.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ch09.vo.BookVO;
import kr.co.ch09.vo.BooksVO;

@Controller
public class BookController {
		
		@GetMapping("/book/list")
		public String list(String text, Model model) {
			
			// 네이버 Open API 요청
			String clientId = "518pgIN5hUn_OfmIwT4N"; //애플리케이션 클라이언트 아이디
	        String clientSecret = "PygQpauGo3"; //애플리케이션 클라이언트 시크릿
	        
			String apiURL = "https://openapi.naver.com/v1/search/book.json?query={text}";    // JSON 결과
			
			URI uri = UriComponentsBuilder
					  .fromUriString("https://openapi.naver.com")
					  .path("/v1/search/book.json")
					  .queryParam("query", text)
					  .queryParam("display", 10)
					  .queryParam("start", 1)
					  .queryParam("sort", "sim")
					  .encode()
					  .build()
					  .toUri();
					  
			RequestEntity<Void> req = RequestEntity
										.get(uri)
										.header("X-Naver-Client-Id", clientId)
										.header("X-Naver-Client-Secret", clientSecret)
										.build();
			
			RestTemplate restTemplate = new RestTemplate();		
			ResponseEntity<String> result = restTemplate.exchange(req, String.class);
			
			
			
			// JSON 파싱
			ObjectMapper om = new ObjectMapper();
			try {
				BooksVO booksVO = om.readValue(result.getBody(), BooksVO.class);
				System.out.println(booksVO);
				
				List<BookVO> books = booksVO.getItems();
				
				model.addAttribute("books", books);
				
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return "/book/list";
		}
}
