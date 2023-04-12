package kr.co.animal.hospital.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.animal.hospital.vo.ItemVO;
import kr.co.animal.hospital.vo.ResultVO;

@Controller
public class MainController {

	@GetMapping(value= {"/","/index"})
	public String index(Model model) throws UnsupportedEncodingException {
		// API 정보
		String apiURL = "http://apis.data.go.kr/6260000/BusanAnimalHospService/getTblAnimalHospital";
		String serviceKey = "IlgHlyFAwzKoFv74rD2HJmBU%2BPBA4ZbufpdmN%2FcRmYzfsq%2FkMtDPYu158Xt45M6JnX%2BO1JklLN%2BQpQQSKLPbhw%3D%3D";
		String resultType = "json";
		String pageNo = "1";
		String numOfRows = "1000";
		
		URI uri = UriComponentsBuilder
							.fromUriString(apiURL)
							.queryParam("serviceKey", serviceKey)
							.queryParam("resultType", resultType)
							.queryParam("pageNo", pageNo)
							.queryParam("numOfRows", numOfRows)
							.encode()
							.build(true)
							.toUri();
		System.out.println(uri.toString());
		
		RequestEntity<Void> req = RequestEntity.get(uri).build();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(req, String.class);
		
		// JSON 문자열
		String jsonData = result.getBody();
		
		// JSON 파싱(Deserialization)
		ObjectMapper om = new ObjectMapper();
		try {
			ResultVO resultVO = om.readValue(jsonData, ResultVO.class);
			List<ItemVO> items = resultVO.getGetTblAnimalHospital().getBody().getItems().getItem();
			
			System.out.println(items);
			
			
			model.addAttribute("items", items);
			
		}catch(JsonMappingException e) {
			e.printStackTrace();
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
