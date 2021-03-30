package com.sbs.untactTeacher.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sbs.untactTeacher.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Helper {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;	
	private String loginPw;
	@JsonIgnore
	private int authLevel;
	@JsonIgnore
	private String authKey;
	private String name;	
	private String cellphoneNo;
	private String email;
	private String sido;	
	private String career;
	private int relId;
	private String extra__thumbImg;		
}
