package kr.doctor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class CheckedPasswdFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int d_num = Integer.parseInt(request.getParameter("d_num"));
		
		request.setAttribute("d_num", d_num);
		
		return "/WEB-INF/views/doctor/checkedPasswdForm.jsp";
	}

}
