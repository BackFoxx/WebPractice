package com.example.WebPractice.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
// Builder 메소드로 오브젝트를 생성할 수 있게 해준다.
@NoArgsConstructor
//매개변수가 없는 생성자를 만들어 준다.
@AllArgsConstructor
//클래스 내의 모든 멤버변수를 매개변수로 받는 생성자를 만들어 준다.
@Data
//getter, setter 메서드를 만들어 준다.
@Entity
@Table(name = "todo")
//이 엔티티는 데이터베이스의 todo 테이블에 매핑된다는 뜻이다.
public class TodoEntity {
 @Id //기본 키가 될 필드에 지정한다.
 @GeneratedValue(generator = "system-uuid")
 //generator ="" 어떤 방식으로 ID를 생성할지 지정한다.
 //system-uuid -> Hibernate가 제공하는 기본 Generator가 아닌 나만의 Generator를 사용하는 경우 이용한다.
 @GenericGenerator(name="system-uuid", strategy = "uuid")
 // uuid를 사용하는 system-uuid라는 이름의 GenericGenerator를 만들고, @GeneratedValue가 참조해 사용한다.
 private String id; //이 오브젝트의 아이디
 private String userId; // 이 오브젝트를 생성한 사용자의 아이디
 private String title; // Todo 타이틀(예 : 운동하기)
 private Boolean done; // true - todo를 완료한 경우(checked)
}
