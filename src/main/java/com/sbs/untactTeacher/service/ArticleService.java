package com.sbs.untactTeacher.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.untactTeacher.dao.ArticleDao;
import com.sbs.untactTeacher.dto.Article;
import com.sbs.untactTeacher.dto.Board;
import com.sbs.untactTeacher.dto.GenFile;
import com.sbs.untactTeacher.dto.HelperOrder;
import com.sbs.untactTeacher.dto.Member;
import com.sbs.untactTeacher.dto.ResultData;
import com.sbs.untactTeacher.util.Util;

@Service
public class ArticleService {
	@Autowired
	private GenFileService genFileService;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberService memberService;

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public ResultData addArticle(Map<String, Object> param) {
		articleDao.addArticle(param);

		int id = Util.getAsInt(param.get("id"), 0);
		
		genFileService.changeInputFileRelIds(param, id);

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		articleDao.deleteArticle(id);

		genFileService.deleteGenFiles("article", id);

		return new ResultData("S-1", "삭제하였습니다.", "id", id);
	}

	public ResultData modifyArticle(Map<String, Object> param) {
		articleDao.modifyArticle(param);
		
		int id = Util.getAsInt(param.get("id"), 0);
		
		return new ResultData("S-1", "게시물을 수정하였습니다.", "id", id);
	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {
		return articleDao.getArticles(searchKeywordType, searchKeyword);
	}

	public ResultData getActorCanModifyRd(Article article, Member actor) {
		if (article.getClientId() == actor.getId()) {
			return new ResultData("S-1", "가능합니다.");
		}

		if (memberService.isAdmin(actor)) {
			return new ResultData("S-2", "가능합니다.");
		}

		return new ResultData("F-1", "권한이 없습니다.");
	}

	public ResultData getActorCanDeleteRd(Article article, Member actor) {
		return getActorCanModifyRd(article, actor);
	}

	public Article getForPrintArticle(int id) {		
		return articleDao.getForPrintArticle(id);		
	}

	public List<Article> getForPrintArticles(int directorId, String searchKeywordType, String searchKeyword, int page,
			int itemsInAPage) {
		
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		
		List<Article> orders = articleDao.getForPrintArticles(directorId, searchKeywordType, searchKeyword, limitStart, limitTake);		
		if(orders.isEmpty()) {			
			return null;
		}
		List<Integer> orderIds = orders.stream().map(article -> article.getId()).collect(Collectors.toList());
		
		Map<Integer, Map<String, GenFile>> filesMap = genFileService.getFilesMapKeyRelIdAndFileNo("article", orderIds, "common", "attachment");
		
		for (Article order : orders) {
			Map<String, GenFile> mapByFileNo = filesMap.get(order.getId());

			if (mapByFileNo != null) {
				order.getExtraNotNull().put("file__common__attachment", mapByFileNo);
			}
		}
		
		return orders;
	}
	
	public List<Article> getForPrintCaleandars(int directorId, String searchKeywordType, String searchKeyword, int page,
			int itemsInAPage) {
		
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		
		List<Article> orders = articleDao.getForPrintCaleandars(directorId, searchKeywordType, searchKeyword, limitStart, limitTake);		
		if(orders.isEmpty()) {			
			return null;
		}
		List<Integer> orderIds = orders.stream().map(article -> article.getId()).collect(Collectors.toList());
		
		Map<Integer, Map<String, GenFile>> filesMap = genFileService.getFilesMapKeyRelIdAndFileNo("article", orderIds, "common", "attachment");
		
		for (Article order : orders) {
			Map<String, GenFile> mapByFileNo = filesMap.get(order.getId());

			if (mapByFileNo != null) {
				order.getExtraNotNull().put("file__common__attachment", mapByFileNo);
			}
		}
		
		return orders;
	}

	public Board getBoard(int id) {
		return articleDao.getBoard(id);
	}

	public ResultData addReply(Map<String, Object> param) {
		articleDao.addReply(param);

		int id = Util.getAsInt(param.get("id"), 0);

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public int setStep2(Integer id) {
		return articleDao.setStep2(id);		
	}

	public List<HelperOrder> getForPrintHelperOrders(int orderId, String searchKeywordType, String searchKeyword,
		int page, int itemsInAPage) {
		
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		
		List<HelperOrder> orders = articleDao.getForPrintHelperOrders(orderId, searchKeywordType, searchKeyword, limitStart, limitTake);		
		if(orders.isEmpty()) {			
			return null;
		}
		/*System.out.println("실행전");
		List<Integer> orderIds = orders.stream().map(helperOrder -> helperOrder.getId()).collect(Collectors.toList());
		System.out.println("실행후");
		Map<Integer, Map<String, GenFile>> filesMap = genFileService.getFilesMapKeyRelIdAndFileNo("article", orderIds, "common", "attachment");
		
		for (HelperOrder order : orders) {
			Map<String, GenFile> mapByFileNo = filesMap.get(order.getId());

			if (mapByFileNo != null) {
				order.getExtraNotNull().put("file__common__attachment", mapByFileNo);
			}
		}*/
		
		return orders;
	}

	public HelperOrder getHelperOrder(int id) {
		return articleDao.getHelperOrder(id);
	}

	public int setHelperOrderStep2(Integer id) {
		return articleDao.setHelperOrderStep2(id);
	}

	public void addOrderHelper(Integer orderId, String name) {
		articleDao.addOrderHelper(orderId, name);
		
	}

	
}
