package kr.review.action;

import java.io.Console;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewCommVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListCommAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		
		int r_num = Integer.parseInt(
				request.getParameter("r_num"));
		
		System.out.println("mm");
		System.out.println(Integer.parseInt(request.getParameter("r_num")));
		
		System.out.println("ggg" + request.getParameter("r_num"));
//		int r_num = Integer.parseInt(
//				request.getParameter("r_num"));
		
		
		
		
		System.out.println(r_num);//61
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int count = dao.getCommReviewCount(r_num);
		
		System.out.println(count);//2
		
		/*
		 * ajax 방식으로 목록을 표시하기 때문에 PagingUtil은 페이지수
		 * 표시가 목적이 아니라 목록 데이터 페이지 처리를 위한 rownum
		 * 번호를 구하는 것이 목적임
		 */
		int rowCount = 10;
		//currentPage,count,rowCount,pageCount,url
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),
				           count,rowCount,1,null);
		
		List<ReviewCommVO> list = null;
		if(count > 0) {
			list = dao.getListCommReview(page.getStartRow(),
					                     page.getEndRow(),
					                     r_num);
		}else {
			list = Collections.emptyList();//빈 List 생성
		}
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		
		Map<String,Object> mapAjax = 
				           new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		//로그인한 사람이 작성자인지 체크하기 위해서 전송
		//댓글 수정, 삭제하기 위해.
		mapAjax.put("user_num", user_num);
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}


