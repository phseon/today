package kr.procedure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;

public class DeleteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션 넣기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null || user_auth!= 1) {
			return "redirect:/member/loginForm.do";
		}
		
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		ProcedureDAO dao = ProcedureDAO.getInstance();
		ProcedureVO pro = dao.getProcedure(p_num);
		
		request.setAttribute("pro", pro);
		return "/WEB-INF/views/procedure/deleteForm.jsp";
	}

}
