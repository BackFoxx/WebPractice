package com.example.WebPractice.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.WebPractice.Model.TodoEntity;
import com.example.WebPractice.service.persistence.TodoRepository;

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

	//Retrieve �޼���
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
		//1. ������ ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�. �� �޼���� 2.3.1 Create Todo���� �����ߴ�.
		valiate(entity);

		//2. �Ѱܹ��� ��ƼƼ id�� �̿��� TodoEntity�� �����´�.
		final Optional<TodoEntity> original = repository.findById(entity.getId());

		if(original.isPresent())
		//3. ��ȯ�� TodoEntity�� �����ϸ� ���� �� entity ������ ���� �����.
		{
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.getDone());

			//4. �����ͺ��̽��� �� ���� �����Ѵ�.
			repository.save(todo);
		}
			//5.retrieve�޼��带 �̿��� ������� ��� Todo ����Ʈ�� �����Ѵ�.
			return retrieve(entity.getUserId());

	}

	//Delete Todo
	public List<TodoEntity> delete(final TodoEntity entity) {
		//1. ������ ��ƼƼ�� ��ȿ���� Ȯ���Ѵ�.
		valiate(entity);
		try {
			//2. ��ƼƼ�� �����Ѵ�.
			repository.delete(entity);
		} catch (Exception e) {
			//3. ���� �߻��� id�� exception�� �α��Ѵ�.
			log.error("Error deleting entity ", entity.getId(), e);
			//4. ��Ʈ�ѷ��� exception�� ������. �����ͺ��̽� ���� ������ ĸ��ȭ�Ϸ��� e�� �������� �ʰ� �� exception ������Ʈ�� �����Ѵ�.
			throw new RuntimeException("error deleting entity " + entity.getId());
		}

		//5. �� Todo ����Ʈ�� ������ �����Ѵ�.
		return retrieve(entity.getUserId());
	}
}
