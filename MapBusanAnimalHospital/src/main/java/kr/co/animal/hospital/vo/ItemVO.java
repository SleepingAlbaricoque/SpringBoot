package kr.co.animal.hospital.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO {
	private String gugun;
	private String road_address;
	private String approval;
	private String tel;
	private String basic_date;
	private String lon;
	private String lat;
	private String animal_hospital;
}
