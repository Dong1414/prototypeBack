package com.sbs.untactTeacher.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untactTeacher.dto.Article;
import com.sbs.untactTeacher.dto.Helper;
import com.sbs.untactTeacher.dto.HelperOrder;
import com.sbs.untactTeacher.dto.Member;
import com.sbs.untactTeacher.dto.ResultData;
import com.sbs.untactTeacher.service.ArticleService;
import com.sbs.untactTeacher.service.MemberService;
import com.sbs.untactTeacher.util.Util;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MemberService memberService;
	

	@GetMapping("/usr/order/accept")
	@ResponseBody
	public ResultData doAccept(Integer id) {
		
		if (id == null) {			
			return new ResultData("F-1", "id를 입력해주세요.");			
		}
		
		Article order = articleService.getForPrintArticle(id);
		
		if (order == null) {
			return new ResultData("F-2", "존재하지 않는 게시물번호 입니다.");
		}
		if (order.getStepLevel() > 1) {
			return new ResultData("F-2", "이미 다른 지도사가 수락한 요청입니다.");
		}
		articleService.setStep2(id);

		return new ResultData("S-1", "요청을 수락하였습니다.", "id", id);
	}
	
	@GetMapping("/usr/helperOrder/accept")
	@ResponseBody
	public ResultData dohelperOrderAccept(Integer id) {
		
		if (id == null) {			
			return new ResultData("F-1", "id를 입력해주세요.");			
		}				
		
		HelperOrder order = articleService.getHelperOrder(id);		
		
		if (order == null) {
			return new ResultData("F-2", "존재하지 않는 게시물번호 입니다.");
		}
		if (order.getStepLevel() > 1) {
			return new ResultData("F-2", "이미 다른 지도사가 수락한 요청입니다.");
		}
		
		articleService.setHelperOrderStep2(id);		
		articleService.addOrderHelper(order.getOrderId() ,order.getName());
		return new ResultData("S-1", "요청을 수락하였습니다.", "id", id);
	}
	
	@GetMapping("/usr/order/detail")
	@ResponseBody
	public ResultData showDetail(Integer id) {
		
		if (id == null) {			
			return new ResultData("F-1", "id를 입력해주세요.");			
		}
		
		Article order = articleService.getForPrintArticle(id);
		System.out.println(id);
		if (order == null) {
			return new ResultData("F-2", "존재하지 않는 게시물번호 입니다.");
		}

		return new ResultData("S-1", "성공", "order", order);
	}
	
	
	@GetMapping("/usr/helperOrder/list")
	@ResponseBody
	public ResultData showHelperOrderList(@RequestParam int id, String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {
				
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}

		if (searchKeyword == null) {
			searchKeywordType = null;
		}
		
		int itemsInAPage = 20;
		System.out.println(id);
		List<HelperOrder> orders = articleService.getForPrintHelperOrders(id, searchKeywordType, searchKeyword, page, itemsInAPage);
		if(orders == null) {			
			return null;
		}		
		System.out.println(orders);
		return new ResultData("S-1", "성공", "helperOrders", orders);
	}

	
	@GetMapping("/usr/caleandar/list")
	@ResponseBody
	public ResultData showCaleandarList(@RequestParam int directorId, String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {
		
		Member member = memberService.getMember(directorId);
		
		if ( member == null ) {
			return new ResultData("F-1", "존재하지 않는 회원 입니다.");
		}
		
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}

		if (searchKeyword == null) {
			searchKeywordType = null;
		}
		
		int itemsInAPage = 20;

		List<Article> orders = articleService.getForPrintCaleandars(directorId, searchKeywordType, searchKeyword, page, itemsInAPage);
		if(orders == null) {			
			return null;
		}
		return new ResultData("S-1", "성공", "orders", orders);
	}
	
	@GetMapping("/usr/order/list")
	@ResponseBody
	public ResultData showList(@RequestParam int directorId, String searchKeywordType, String searchKeyword, @RequestParam(defaultValue = "1") int page) {
		
		Member member = memberService.getMember(directorId);
		
		if ( member == null ) {
			return new ResultData("F-1", "존재하지 않는 회원 입니다.");
		}
		
		if (searchKeywordType != null) {
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
		}

		if (searchKeyword == null) {
			searchKeywordType = null;
		}
		
		int itemsInAPage = 20;

		List<Article> orders = articleService.getForPrintArticles(directorId, searchKeywordType, searchKeyword, page, itemsInAPage);
		if(orders == null) {			
			return null;
		}
		return new ResultData("S-1", "성공", "orders", orders);
	}
	
	@PostMapping("/usr/article/doAddReply")
	@ResponseBody
	public ResultData doAddReply(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");

		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		
		if (param.get("articleId") == null) {
			return new ResultData("F-1", "articleId를 입력해주세요.");
		}

		param.put("memberId", loginedMemberId);

		return articleService.addReply(param);
	}

	@PostMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		
		if (param.get("title") == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}

		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}

		param.put("memberId", loginedMemberId);

		return articleService.addArticle(param);
	}

	@PostMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(Integer id, HttpServletRequest req) {
		Member loginedMember = (Member)req.getAttribute("loginedMember");

		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}

		ResultData actorCanDeleteRd = articleService.getActorCanDeleteRd(article, loginedMember);

		if (actorCanDeleteRd.isFail()) {
			return actorCanDeleteRd;
		}

		return articleService.deleteArticle(id);
	}

	@PostMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		Member loginedMember = (Member)req.getAttribute("loginedMember");
		
		int id = Util.getAsInt(param.get("id"), 0);

		if ( id == 0 ) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}

		if ( Util.isEmpty(param.get("title")) ) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}

		if ( Util.isEmpty(param.get("body")) ) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}

		ResultData actorCanModifyRd = articleService.getActorCanModifyRd(article, loginedMember);

		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}

		return articleService.modifyArticle(param);
	}
}
