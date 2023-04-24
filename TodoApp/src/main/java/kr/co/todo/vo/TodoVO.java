package kr.co.todo.vo;

import lombok.Data;

@Data
public class TodoVO {
	private int no;
	private String content;
	private int status;
	private String rdate;
}
