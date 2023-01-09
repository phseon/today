package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.myinfo.dao.MyInfoDAO;
import kr.review.vo.ReviewVO;

public class ReviewInfoListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.jsp";
		} 

		MyInfoDAO dao = MyInfoDAO.getInstance();
		ReviewVO myReview = dao.getReviewInfo(user_num);
		request.setAttribute("myReview", myReview);
		
		
		return "/WEB-INF/views/myinfo/reviewInfoList.jsp";
	}

}
