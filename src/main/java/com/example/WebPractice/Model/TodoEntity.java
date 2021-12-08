package com.example.WebPractice.Model;

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
public class TodoEntity {
 private String id; //이 오브젝트의 아이디
 private String userId; // 이 오브젝트를 생성한 사용자의 아이디
 private String title; // Todo 타이틀(예 : 운동하기)
 private Boolean done; // true - todo를 완료한 경우(checked)
}
