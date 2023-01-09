package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.reservation.vo.ReservationVO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.StringUtil;

public class DetailRevAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//예약번호 반환
		int rev_num = Integer.parseInt(
						request.getParameter("rev_num"));
		ReviewDAO dao = ReviewDAO.getInstance();
		
		ReservationVO rez = dao.getRevInfo(rev_num);
		
		
//		int rev_num = Integer.parseInt(
//				request.getParameter("rev_num"));
//		ReviewDAO rez_dao = ReviewDAO.getInstance();
//		
//		ReservationVO rez = rez_dao.getRevInfo(rev_num);

		
		
		//HTML 태그를 허용하지 않음
		rez.setRev_num(rez.getRev_num());
		rez.setM_num(rez.getM_num());
	
		request.setAttribute("rez", rez);
//		request.setAttribute("rev_num", rez.getRev_num());
//		request.setAttribute("m_num", rez.getM_num());
//		request.setAttribute("gm_num", rev_num);
//		request.setAttribute("rez", rez);
		
		return "/WEB-INF/views/review/detail.jsp";
	}

}
