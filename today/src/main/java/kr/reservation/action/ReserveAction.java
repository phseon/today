package kr.reservation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.reservation.dao.ReservationDAO;
import kr.reservation.vo.ReservationVO;

public class ReserveAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 X
			return "redirect:/member/lofinForm.do";
		}
				
		//회원 여부 확인
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=2) {
			return "redirect:/main/main.do";
		}
		
		request.setCharacterEncoding("utf-8");
		
		ReservationDAO dao = ReservationDAO.getInstance();
		ReservationVO reserve = new ReservationVO();
		reserve.setM_num(user_num);
		reserve.setP_num(Integer.parseInt(request.getParameter("p_num")));
		reserve.setRev_date(request.getParameter("date"));
		reserve.setRev_time(request.getParameter("time"));
		reserve.setDr_num(Integer.parseInt(request.getParameter("dr_num")));
		
		dao.insertReservation(reserve);
		
		request.setAttribute("accessMsg", "예약이 완료되었습니다.");
		request.setAttribute("accessUrl",
						request.getContextPath() + "/myinfo/myPage.do");
				
		return "/WEB-INF/views/common/notice.jsp";
	}

}
