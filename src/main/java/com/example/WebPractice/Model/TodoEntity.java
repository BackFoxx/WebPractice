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
// Builder �޼ҵ�� ������Ʈ�� ������ �� �ְ� ���ش�.
@NoArgsConstructor
//�Ű������� ���� �����ڸ� ����� �ش�.
@AllArgsConstructor
//Ŭ���� ���� ��� ��������� �Ű������� �޴� �����ڸ� ����� �ش�.
@Data
//getter, setter �޼��带 ����� �ش�.
@Entity
@Table(name = "Todo")
public class TodoEntity {
 @Id
 @GeneratedValue(generator = "system-uuid")
 @GenericGenerator(name="system-uuid", strategy = "uuid")
 private String id; //�� ������Ʈ�� ���̵�
 private String userId; // �� ������Ʈ�� ������ ������� ���̵�
 private String title; // Todo Ÿ��Ʋ(�� : ��ϱ�)
 private Boolean done; // true - todo�� �Ϸ��� ���(checked)
}
