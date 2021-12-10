package com.example.WebPractice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebPractice.dto.ResponseDTO;
import com.example.WebPractice.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	/*
	 * TodoController 내에 선언된 TodoService에 @Autowired 어노테이션이 붙어 있는 것을 확인한다.
	 * @Autowired가 알아서 빈을 찾은 다음 그 빈을 이 인스턴스 멤버 변수에 연결하라는 뜻이다.
	 * */
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService(); // 테스트 서비스 사용
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
}
