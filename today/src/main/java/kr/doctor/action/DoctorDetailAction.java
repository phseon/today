package kr.doctor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.doctor.dao.DoctorDAO;
import kr.doctor.vo.DoctorVO;
import kr.member.vo.MemberVO;

public class DoctorDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int d_num = Integer.parseInt(request.getParameter("d_num"));
		
		DoctorDAO doctorDao = DoctorDAO.getInstance();
		MemberVO doctor = new MemberVO();
		doctor = doctorDao.getDoctorDetail(d_num);
		
		request.setAttribute("doctor", doctor);
		
		
		return "/WEB-INF/views/doctor/doctorDetail.jsp";
	}

}
