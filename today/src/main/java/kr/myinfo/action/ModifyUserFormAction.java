package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.myinfo.dao.MyInfoDAO;

public class ModifyUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// 세션 여부 확인하기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/login.do";
		}
		
		MyInfoDAO dao = MyInfoDAO.getInstance();
		MemberVO member = dao.getMemberInfo(user_num);
		request.setAttribute("member", member);
		
		
		return "/WEB-INF/views/myinfo/modifyUserForm.jsp";
	}

}
