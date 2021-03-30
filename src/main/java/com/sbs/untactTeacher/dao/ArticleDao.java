package com.sbs.untactTeacher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untactTeacher.dto.Article;
import com.sbs.untactTeacher.dto.Board;
import com.sbs.untactTeacher.dto.HelperOrder;

@Mapper
public interface ArticleDao {
	Article getArticle(@Param("id") int id);

	void addArticle(Map<String, Object> param);

	void deleteArticle(@Param("id") int id);

	void modifyArticle(Map<String, Object> param);

	List<Article> getArticles(@Param("searchKeywordType") String searchKeywordType,
			@Param("searchKeyword") String searchKeyword);

	Article getForPrintArticle(@Param("id") int id);

	List<Article> getForPrintArticles(@Param("directorId") int boardId,
			@Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword,
			@Param("limitStart") int limitStart, @Param("limitTake") int limitTake);

	List<Article> getForPrintCaleandars(@Param("directorId") int boardId,
			@Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword,
			@Param("limitStart") int limitStart, @Param("limitTake") int limitTake);
	
	Board getBoard(@Param("id") int id);

	void addReply(Map<String, Object> param);

	int setStep2(@Param("id") Integer id);

	List<HelperOrder> getForPrintHelperOrders(@Param("orderId") int boardId,
			@Param("searchKeywordType") String searchKeywordType, @Param("searchKeyword") String searchKeyword,
			@Param("limitStart") int limitStart, @Param("limitTake") int limitTake);

	HelperOrder getHelperOrder(@Param("id") Integer id);

	int setHelperOrderStep2(@Param("id") Integer id);
}
