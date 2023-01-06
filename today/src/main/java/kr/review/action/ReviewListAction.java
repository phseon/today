package kr.review.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ReviewListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		
		//keyfield,keyword를 받아 검색에 사용
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		ReviewDAO dao = ReviewDAO.getInstance();
		int count = dao.getReviewCount(keyfield, keyword);
		
		//페이지 처리
		//keyfield,keyword,currentPage,count,rowCount(한 화면 게시글 개수),
		//pageCount,url
		PagingUtil page =
				new PagingUtil(keyfield,keyword,
						Integer.parseInt(pageNum),
							count,20,10,"reviewPage.do");
		//목록처리
		List<ReviewVO> list = null;
		if(count > 0) {
			list = dao.getListReview(page.getStartRow(),
									page.getEndRow(),
									keyfield,keyword);
		}
		//JSP에서 사용할 수 있도록 넘겨줌
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		return "/WEB-INF/views/review/reviewList.jsp";
	}

}
