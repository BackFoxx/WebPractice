package com.example.WebPractice.persisance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.WebPractice.Model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> 
//ù ��° �Ű����� : ���̺� ���ε� ��ƼƼ Ŭ����
//�� ��° �Ű����� : �� ��ƼƼ�� �⺻ Ű�� Ÿ��
//�츮�� ���ε� ��ƼƼ Ŭ������ TodoEntity, �⺻ Ű id�� Ÿ���� String�̹Ƿ� String�� �־��־���.
{
	@Query("select * from Todo t where t.userId = ?1")
	//?1�� �޼����� �Ű������� ���� ��ġ��.
	//������ ������ JPA�� �޼��� �̸��� �Ľ��ؼ� ���� ���� ������ �ۼ��� �����Ѵ�.
	List<TodoEntity> findByUserId(String userId);
}
