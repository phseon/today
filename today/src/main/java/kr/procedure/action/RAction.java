package kr.procedure.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
//import kr.member.vo.MemberVO;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;

public class RAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		if(user_num == null || user_auth!= 1) {//2로 바꾸기
			return "redirect:/member/loginForm.do";
		}
		List<ProcedureVO> list = null;
		//List<MemberVO> m_list = null;
		ProcedureDAO dao = ProcedureDAO.getInstance();
		list = dao.select(0,0);
		//m_list = dao.doctor();
		
		
		request.setAttribute("list", list);
		//request.setAttribute("m_list", m_list);
		request.setAttribute("p_num", p_num);
		return "/WEB-INF/views/procedure/r.jsp";
	}

}
