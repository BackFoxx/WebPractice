package com.example.WebPractice.service;
import com.sun.tools.javac.comp.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.WebPractice.Model.TodoEntity;
import com.example.WebPractice.persistence.TodoRepository;

@Slf4j
@Service
// 비즈니스 로직을 수행하는 서비스 레이어임을 알려준다.
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("My first todo ff item").build();
		
		repository.save(entity);
		
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	//CreatrTodo 메서드
	public List<TodoEntity> create(final TodoEntity entity) {
		//검증
		valiate(entity);

		//저장
		repository.save(entity);
		log.info("Entity Id : {} is saved", entity.getId());

		//저장된 엔티티를 포함하는 새 리스트를 반환
		return repository.findByUserId(entity.getUserId());
	}

	//Retrieve 메서드
	public List<TodoEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}

	private void valiate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getUserId() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}

	//Update Todo
	public List<TodoEntity> update(final TodoEntity entity) {
		//1. 저장할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
		valiate(entity);

		//2. 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());

		if(original.isPresent())
		//3. 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
		{
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.getDone());

			//4. 데이터베이스에 새 값을 저장한다.
			repository.save(todo);
		}
			//5.retrieve메서드를 이용해 사용자의 모든 Todo 리스트를 리턴한다.
			return retrieve(entity.getUserId());

	}

	//Delete Todo
	public List<TodoEntity> delete(final TodoEntity entity) {
		//1. 저장할 엔티티가 유효한지 확인한다.
		valiate(entity);
		try {
			//2. 엔티티를 삭제한다.
			repository.delete(entity);
		} catch (Exception e) {
			//3. 예외 발생시 id와 exception을 로깅한다.
			log.error("Error deleting entity ", entity.getId(), e);
			//4. 컨트롤러로 exception을 보낸다. 데이터베이스 내부 로직을 캡슐화하려면 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
			throw new RuntimeException("error deleting entity " + entity.getId());
		}

		//5. 새 Todo 리스트를 가져와 리턴한다.
		return retrieve(entity.getUserId());
	}
}
