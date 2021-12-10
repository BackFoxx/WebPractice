package com.example.WebPractice.Model;

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
public class TodoEntity {
 private String id; //�� ������Ʈ�� ���̵�
 private String userId; // �� ������Ʈ�� ������ ������� ���̵�
 private String title; // Todo Ÿ��Ʋ(�� : ��ϱ�)
 private Boolean done; // true - todo�� �Ϸ��� ���(checked)
}
