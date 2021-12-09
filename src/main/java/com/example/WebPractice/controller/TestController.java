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
// REST API�� �����ϹǷ� �� ��Ʈ�ѷ��� RestController���� �˸���. http�� ���õ� �ڵ� �� ��û/���� ������ �˾Ƽ� ���ش�.
@RequestMapping("test") 
public class TestController {

	@GetMapping
	// localhost:8080/test�� Get�޼��尡 testController()�� ����ȴٴ� ���̴�.
	public String testController() {
		return "Hello World!";
	}
	
	@GetMapping("/testGetMapping")
	// localhost:8080/test/testGetMapping�� Get�޼��尡 testControllerWithPath()�� ����ȴٴ� ���̴�.
	public String testControllerWithPath() {
		return "Hello world! testMapping";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariavles(@PathVariable(required = false) int id) {
		// localhost:8080/test/���� id�� ���� �޼ҵ��� int id�� �Ѿ�ٴ� ���̴�.
		// PathVariable -> URI ��η� �Ѿ���� ���� ������ �޴´�.
		// required = false -> �������� �� ���� �ʾƵ� �ȴٴ� ���̴�.
		return "Hello World! ID" + id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required = false) int id) {
		// localhost:8080/test/testRequestParam?������=���� id�� ���� �޼ҵ��� int id�� �Ѿ�ٴ� ���̴�.
		return "Hello World! ID" + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) 
	// @RequestBody TestRequestBodyDTO testRequestBodyDTO : RequestBody�� �������� JSON�� TestRequestBodyDTO�� ��ȯ�� ��������� ���̴�.
	/*
	 * ��û �ٵ�� TestRequestBody�� ���¿� �°� JSON������ ���ڿ��� �Ѱ��ָ�
	 * ���ڿ��� TestRequestBodyDTO ������Ʈ�� �°� ��ȯ�Ͽ�
	 * id ��, message���� �����´�.
	 * */
	{
		return "Hello World! ID " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<String>();
		list.add("Hello World! I'm ResponseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		// ResponseDTO ��ü�� JSON�� ���·� ��ȯ�Ѵ�.
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		// �ٵ� �Ӹ� �ƴ϶� ���� �ٸ� �Ű��������� ������ �� �ִ�.
		List<String> list = new ArrayList<>();
		list.add("Hello World! I'm ResponseEntity. And you got 400!");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
		//badRequest : �߸��� ��û 400
	}
	
	
}
