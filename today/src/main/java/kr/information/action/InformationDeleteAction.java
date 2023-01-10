package kr.information.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.information.dao.InformationDAO;
import kr.information.vo.InformationVO;

public class InformationDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num== null) {
			return "redirect:/member/loginForm.do";
		}
		
		int info_num = Integer.parseInt(request.getParameter("info_num"));
		InformationDAO dao = InformationDAO.getInstance();
		InformationVO db_info = dao.getinfodetail(info_num);
		if(user_num != db_info.getM_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		dao.deleteInfo(info_num);
		
		
		return "redirect:/information/list.do";
	}

}
