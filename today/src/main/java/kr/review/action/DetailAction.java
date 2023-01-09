package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.reservation.dao.ReservationDAO;
import kr.reservation.vo.ReservationVO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int r_num = Integer.parseInt(
						request.getParameter("r_num"));
		ReviewDAO dao = ReviewDAO.getInstance();
		
		ReviewVO review = dao.getReview(r_num);
		
		
//		int rev_num = Integer.parseInt(
//				request.getParameter("rev_num"));
//		ReviewDAO rez_dao = ReviewDAO.getInstance();
//		
//		ReservationVO rez = rez_dao.getRevInfo(rev_num);

		
		
		//HTML 태그를 허용하지 않음
		review.setR_content(StringUtil.useBrNoHtml(review.getR_content()));
		
	
		request.setAttribute("review", review);
//		request.setAttribute("rez", rez);
		
		return "/WEB-INF/views/review/detail.jsp";
	}

}
