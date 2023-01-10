package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.doctor.vo.DoctorVO;
import kr.member.vo.MemberVO;
import kr.myinfo.dao.MyInfoDAO;
import kr.reservation.vo.ReservationVO;
import kr.review.vo.ReviewVO;

public class ReservationInfoListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/login.do";
		} 

		MyInfoDAO dao = MyInfoDAO.getInstance();
		ReservationVO myReservation = dao.getReservationInfo(user_num);
		request.setAttribute("myReservation", myReservation); 

		
		//p_num을 보내주어서 procedure테이블에 있는 p_num에 대한 p_title 뽑아내기
		Integer p_num = (Integer)myReservation.getP_num();
		DoctorVO myProcedure = dao.getProcedureInfo(p_num);
		request.setAttribute("myProcedure", myProcedure); 
		
	
		
		return "/WEB-INF/views/myinfo/reservationInfoList.jsp";
	}

}
