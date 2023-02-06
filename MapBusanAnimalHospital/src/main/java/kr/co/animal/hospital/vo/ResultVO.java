package kr.co.animal.hospital.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
	private GetTblAnimalHospital getTblAnimalHospital;
	
	@Getter
	@Setter
	public class GetTblAnimalHospital{
		private Header header;
		private Body body;
		
		@Getter
		@Setter
		public class Header{
			private String resultMsg;
			private String resultCode;
		}
		
		@Getter
		@Setter
		public class Body{
			private Items items;
			private int numOfRows;
			private int pageNo;
			private int totalCount;
			
			@Getter
			@Setter
			public class Items{
				private List<ItemVO> item;
			}
		}
	}
}
