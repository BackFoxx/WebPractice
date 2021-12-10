package com.example.WebPractice.dto;

import com.example.WebPractice.Model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
			this.id = entity.getId();
			this.title = entity.getTitle();
			this.done = entity.getDone();
	}

	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
	//DTO를 Entity로 변환해줌
}
