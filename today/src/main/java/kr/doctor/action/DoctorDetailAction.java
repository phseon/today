package kr.doctor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class DoctorDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*request.setCharacterEncoding("utf-8");
		
		int m_num = Integer.parseInt(request.getParameter("m_num"));
		
		DoctorDAO doctorDao = DoctorDAO.getInstance();
		MemberVO doctor = new MemberVO();
		doctor = doctorDao.getDoctorDetail(m_num);
		
		request.setAttribute("doctor", doctor);
		*/
		
		return "/WEB-INF/views/doctor/doctorDetail.jsp";
	}

}
