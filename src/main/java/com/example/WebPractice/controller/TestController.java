package com.example.WebPractice.controller;

import com.example.WebPractice.dto.ResponseDTO;
import com.example.WebPractice.dto.TestRequestBodyDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController 
// REST API를 구현하므로 이 컨트롤러가 RestController임을 알린다. http와 관련된 코드 및 요청/응답 매핑을 알아서 해준다.
@RequestMapping("test") 
public class TestController {

	@GetMapping
	// localhost:8080/test의 Get메서드가 testController()에 연결된다는 뜻이다.
	public String testController() {
		return "Hello World!";
	}
	
	@GetMapping("/testGetMapping")
	// localhost:8080/test/testGetMapping의 Get메서드가 testControllerWithPath()에 연결된다는 뜻이다.
	public String testControllerWithPath() {
		return "Hello world! testMapping";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariavles(@PathVariable(required = false) int id) {
		// localhost:8080/test/변수 id의 값이 메소드의 int id로 넘어간다는 뜻이다.
		// PathVariable -> URI 경로로 넘어오는 값을 변수로 받는다.
		// required = false -> 변수값을 꼭 넣지 않아도 된다는 뜻이다.
		return "Hello World! ID" + id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		// localhost:8080/test/testRequestParam?변수명=변수 id의 값이 메소드의 int id로 넘어간다는 뜻이다.
		return "Hello World! ID" + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) 
	// @RequestBody TestRequestBodyDTO testRequestBodyDTO : RequestBody로 보내오는 JSON을 TestRequestBodyDTO로 변환해 가져오라는 뜻이다.
	/*
	 * 요청 바디로 TestRequestBody의 형태에 맞게 JSON형태의 문자열을 넘겨주면
	 * 문자열을 TestRequestBodyDTO 오브젝트에 맞게 변환하여
	 * id 값, message값을 가져온다.
	 * */
	{
		return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<String>();
		list.add("Hello World! I'm ResponseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		// ResponseDTO 자체를 JSON의 형태로 반환한다.
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		// 바디 뿐만 아니라 여러 다른 매개변수들을 조작할 수 있다.
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
		//badRequest : 잘못된 요청 400
	}
	
	
}
