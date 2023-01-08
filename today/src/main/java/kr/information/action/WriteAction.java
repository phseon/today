package kr.information.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.information.dao.InformationDAO;
import kr.information.vo.InformationVO;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth= (Integer)session.getAttribute("user_auth");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}else if(user_auth==2) {
			return "/WEB-INF/views/information/autherror.jsp";
		}
		
		InformationVO info = new InformationVO();
		info.setInfo_title(request.getParameter("title"));
		info.setInfo_content(request.getParameter("content"));
		info.setM_num(user_num);
		
		InformationDAO dao = InformationDAO.getInstance();
		dao.insertInformation(info);
		
		return "/WEB-INF/views/information/write.jsp";
	}
	
}
