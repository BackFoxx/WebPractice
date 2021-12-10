package com.example.WebPractice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebPractice.Model.TodoEntity;
import com.example.WebPractice.persisance.TodoRepository;

@Service
// ����Ͻ� ������ �����ϴ� ���� ���̾����� �˷��ش�.
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		// TodoEntity ����
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		// TodoEntity ����
		repository.save(entity);
		// TodoEntity �˻�
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
}
