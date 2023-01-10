package kr.information.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.information.dao.InformationDAO;
import kr.information.vo.InformationVO;
import kr.util.StringUtil;

public class InformationUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//글번호 반환
		int info_num = Integer.parseInt(request.getParameter("info_num"));
		
		InformationDAO dao = InformationDAO.getInstance();
		InformationVO info = dao.getinfodetail(info_num);
		
		if(user_num != info.getM_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		info.setInfo_title(StringUtil.parseQuot(info.getInfo_title()));
		request.setAttribute("info", info);
		
		
		
		return "/WEB-INF/views/information/informationUpdateForm.jsp";
	}

}
