package com.example.WebPractice.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
}
