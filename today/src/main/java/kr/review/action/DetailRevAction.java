package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.reservation.vo.ReservationVO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;

public class DetailRevAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//리뷰번호 반환
		int r_num = Integer.parseInt(
						request.getParameter("r_num"));
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		//예약, 리뷰 VO 둘 다 사용
		ReservationVO rez = dao.getRevInfo(user_num);
		ReviewVO review = dao.getReview(r_num);
	
		request.setAttribute("rez", rez);
		request.setAttribute("review", review);
		
		return "/WEB-INF/views/review/detail.jsp";
	}

}
