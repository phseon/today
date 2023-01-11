package kr.procedure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.reservation.dao.ReservationDAO;
import kr.reservation.vo.ReservationVO;

public class ReAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null || user_auth!= 1) {//2로 바꾸기
			return "redirect:/member/loginForm.do";
		}
		Integer p_num = Integer.parseInt(request.getParameter("r_name"));
		String r_date = request.getParameter("rev_date");
		String time = request.getParameter("time");
		String r_ox = request.getParameter("r_ox");
		if(r_ox == null) {
			r_ox = "F";
		}
		ReservationDAO Rdao = new ReservationDAO();
		ReservationVO Rvo = new ReservationVO();
		Rvo.setM_num(user_num);
		Rvo.setP_num(p_num);
		Rvo.setRev_date(r_date);
		Rvo.setRev_time(time);
		Rvo.setR_ox(r_ox);
		
		Rdao.insertReservation(Rvo);
		return "redirect:/procedure/list.do";
	}

}
