package com.example.WebPractice.persisance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.WebPractice.Model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> 
//첫 번째 매개변수 : 테이블에 매핑될 엔티티 클래스
//두 번째 매개변수 : 이 엔티티의 기본 키의 타입
//우리는 매핑될 엔티티 클래스로 TodoEntity, 기본 키 id의 타입이 String이므로 String을 넣어주었다.
{
	@Query("select * from Todo t where t.userId = ?1")
	//?1은 메서드의 매개변수의 순서 위치다.
	//스프링 데이터 JPA가 메서드 이름을 파싱해서 위와 같은 쿼리를 작성해 실행한다.
	List<TodoEntity> findByUserId(String userId);
}
