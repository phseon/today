package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.myinfo.dao.MyInfoDAO;
import kr.review.vo.ReviewVO;

public class ReviewInfoListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*	
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "";
		} */

		MyInfoDAO dao = MyInfoDAO.getInstance();
		//MemberVO member = dao.getMemberInfo(user_num);
		ReviewVO myReview = dao.getReviewInfo(1);
		request.setAttribute("myReview", myReview);
		
		
		return "/WEB-INF/views/myinfo/reviewInfoList.jsp";
	}

}
