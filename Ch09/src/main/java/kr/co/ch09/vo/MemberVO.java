package kr.co.ch09.vo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="member")
public class MemberVO {
	@Id
	private String uid;
	private String pass;
	private String name;
	private String hp;
	private String pos;
	private int dep;
	private LocalDateTime rdate;
}
