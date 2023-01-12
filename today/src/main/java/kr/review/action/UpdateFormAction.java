package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.reservation.vo.ReservationVO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우		
		//예약테이블의 회원번호 반환
				int r_num = Integer.parseInt(
								request.getParameter("r_num"));
				
				ReviewDAO dao = ReviewDAO.getInstance();
				
				ReservationVO rez = dao.getRevInfo(user_num);
				ReviewVO review = dao.getReview(r_num);
				
				if(user_num!=rez.getM_num()) {
					//로그인한 회원번호와 작성자, 회원번호가 불일치
					return "/WEB-INF/views/common/notice.jsp";
				}
				
//				로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호가 일치
				request.setAttribute("rez", rez);
				request.setAttribute("review", review);
				
				return "/WEB-INF/views/review/updateForm.jsp";
	}
	
}
