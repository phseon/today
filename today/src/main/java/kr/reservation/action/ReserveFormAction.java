package kr.reservation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.doctor.vo.DoctorVO;
//import kr.member.vo.MemberVO;
import kr.reservation.dao.ReservationDAO;

public class ReserveFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 X
			return "redirect:/member/loginForm.do";
		}
		
		//회원 여부 확인
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=2) {
			return "redirect:/main/main.do";
		}
		
		//시술 정보 전송
		ReservationDAO dao = ReservationDAO.getInstance();
		List<DoctorVO> list = null;
		list = dao.getProcedureList();
		//List<MemberVO> d_list = null;
		//d_list = dao.getDoctorName();
		
		request.setAttribute("list", list);
		//request.setAttribute("d_list", d_list);
		return "/WEB-INF/views/reservation/reserveForm.jsp";
	}

}
