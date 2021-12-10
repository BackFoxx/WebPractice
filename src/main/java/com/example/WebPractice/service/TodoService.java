package com.example.WebPractice.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.WebPractice.Model.TodoEntity;
import com.example.WebPractice.persistence.TodoRepository;

@Slf4j
@Service
// ����Ͻ� ������ �����ϴ� ���� ���̾����� �˷��ش�.
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("My first todo ff item").build();
		
		repository.save(entity);
		
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	//CreatrTodo �޼���
	public List<TodoEntity> create(final TodoEntity entity) {
		//����
		valiate(entity);

		//����
		repository.save(entity);
		log.info("Entity Id : {} is saved", entity.getId());

		//����� ��ƼƼ�� �����ϴ� �� ����Ʈ�� ��ȯ
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
