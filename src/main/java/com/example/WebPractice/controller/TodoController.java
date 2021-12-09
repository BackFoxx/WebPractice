package com.example.WebPractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebPractice.dto.TodoDTO;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@GetMapping
	ResponseEntity<?> TodoResponseEntity() {
		TodoDTO Todo = TodoDTO.builder()
				.id("Joang")
				.title("°³²­ ¸Ô±â")
				.done(true)
				.build();
		return ResponseEntity.ok().body(Todo);
	}
}
