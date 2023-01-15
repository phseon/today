package kr.doctor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.doctor.dao.DoctorDAO;
import kr.member.vo.MemberVO;

public class CheckedPasswdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 X
			return "redirect:/member/loginForm.do";
		}
				
		//auth 관리자 여부 확인
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=1) {
			return "redirect:/main/main.do";
		}
		
		String passwd = request.getParameter("passwd");
		
		DoctorDAO dao = DoctorDAO.getInstance();
		MemberVO db_doctor = dao.getDoctorDetail(user_num);
		boolean check = false;
		if(db_doctor!=null) {
			check = db_doctor.isCheckedPassword(passwd);
		}
		
		int d_num = Integer.parseInt(request.getParameter("d_num"));
		request.setAttribute("d_num", d_num);
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/doctor/checkedPasswd.jsp";
	}

}
