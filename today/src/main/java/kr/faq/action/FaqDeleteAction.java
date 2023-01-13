package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.faq.dao.FaqDAO;

import kr.controller.Action;


public class FaqDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인x
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int faq_num = Integer.parseInt(request.getParameter("faq_num"));
		FaqDAO dao = FaqDAO.getInstance();
		
		
		 Integer user_auth = (Integer)session.getAttribute("user_auth");

		if(user_auth != 1) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
				
		//로그인한 회원번호와 작성자 회원번호가 일치 -글삭제
	
			dao.deleteFaq(faq_num);
	
		
		
				
			return "/WEB-INF/views/faq/faqdelete.jsp";
	}

}
