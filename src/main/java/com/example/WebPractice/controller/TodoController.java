// 컨트롤러

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

	//CreateTodo
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user"; //temporary user id

			//1. TodoEntity로 변환한다.
			TodoEntity entity = TodoDTO.toEntity(dto);
			//2. id를 null로 초기화한다.
			entity.setId(null);
			//3. 임시 사용자 아이디를 설정해준다.
			entity.setUserId(temporaryUserId);
			//4. 서비스를 이용해 Todo 엔티티를 설정한다.
			List<TodoEntity> entities = service.create(entity);
			//5. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			//6. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			//7. ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			//예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	//RetrieveTodoList
	@GetMapping
	public ResponseEntity<?> retrieveTodoList() {
		String temporaryUserId = "temporary-user"; //temporary user id

		//1. 서비스 메서드의 retrieve() 메서드를 사용해 Todo 리스트를 가져온다.
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		//2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		//3. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		//4. ResonseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}

	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
		String temporaryUserId = "temporary-user";

		//1. dto를 entity로 변환한다.
		TodoEntity entity = TodoDTO.toEntity(dto);
		//2. id를 temporaryUserId로 초기화한다.
		entity.setUserId(temporaryUserId);
		//3. 서비스를 이용해 entity를 업데이트한다.
		List<TodoEntity> entities = service.update(entity);
		//4. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		//5. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		//6. ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
}
