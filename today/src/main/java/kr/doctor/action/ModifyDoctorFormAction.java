package kr.doctor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.doctor.dao.DoctorDAO;
import kr.doctor.vo.DoctorVO;
import kr.member.vo.MemberVO;

public class ModifyDoctorFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 X
			return "redirect:/member/lofinForm.do";
		}
		
		//auth 관리자 여부 확인
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=1) {
			return "redirect:/main/main.do";
		}
		
		//의사번호 전송
		int m_num = Integer.parseInt(request.getParameter("d_num"));
		
		DoctorDAO doctorDao = DoctorDAO.getInstance();
		MemberVO doctor = new MemberVO();
		doctor = doctorDao.getDoctorDetail(m_num);
		
		request.setAttribute("doctor", doctor);
		
		return "/WEB-INF/views/doctor/modifyDoctorForm.jsp";
	}

}
