package com.sbs.untactTeacher.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelperOrder {
	private int id;
	private String regDate;
	private String updateDate;		
	private int orderId;
	private int helperid;	
	private String name;
	private String career;	
	private String sido;	
	private int stepLevel;
	private String extra__writer;
	private String extra__cellphoneNo;	
	
	private Map<String, Object> extra;

	public Map<String, Object> getExtraNotNull() {
		if ( extra == null ) {
			extra = new HashMap<String, Object>();
		}
		
		return extra;
	}
}
