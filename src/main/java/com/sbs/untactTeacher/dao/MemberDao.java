package com.sbs.untactTeacher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untactTeacher.dto.Helper;
import com.sbs.untactTeacher.dto.HelperOrder;
import com.sbs.untactTeacher.dto.Member;

@Mapper
public interface MemberDao {
	void join(Map<String, Object> param);

	void helperJoin(Map<String, Object> param);
	
	Member getMember(@Param("id") int id);
	
	Member getMemberByLoginId(@Param("loginId") String loginId);
	
	Helper getHelperByLoginId(@Param("loginId") String loginId);

	void modifyMember(Map<String, Object> param);

	Member getMemberByAuthKey(@Param("authKey") String authKey);

	List<Member> getForPrintMembers(Map<String, Object> param);

	Member getForPrintMember(@Param("id") int id);
	
	Helper getHelper(@Param("id") int id);

	List<Helper> getForPrintHelperOrdeStep2(@Param("id") Integer id); 

	
}
