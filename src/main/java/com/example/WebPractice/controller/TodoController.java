package com.example.WebPractice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.WebPractice.Model.TodoEntity;
import com.example.WebPractice.dto.TodoDTO;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WebPractice.dto.ResponseDTO;
import com.example.WebPractice.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	/*
	 * TodoController ���� ����� TodoService�� @Autowired ������̼��� �پ� �ִ� ���� Ȯ���Ѵ�.
	 * @Autowired�� �˾Ƽ� ���� ã�� ���� �� ���� �� �ν��Ͻ� ��� ������ �����϶�� ���̴�.
	 * */
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService(); // �׽�Ʈ ���� ���
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user"; //temporary user id

			//1. TodoEntity�� ��ȯ�Ѵ�.
			TodoEntity entity = TodoDTO.toEntity(dto);
			//2. id�� null�� �ʱ�ȭ�Ѵ�.
			entity.setId(null);
			//3. �ӽ� ����� ���̵� �������ش�.
			entity.setUserId(temporaryUserId);
			//4. ���񽺸� �̿��� Todo ��ƼƼ�� �����Ѵ�.
			List<TodoEntity> entities = service.create(entity);
			//5. �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO�� ��ȯ�Ѵ�.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			//6. ��ȯ�� TodoDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ�Ѵ�.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			//7. ResponseDTO�� �����Ѵ�.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			//���ܰ� �ִ� ��� dto ��� error�� �޽����� �־� �����Ѵ�.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
