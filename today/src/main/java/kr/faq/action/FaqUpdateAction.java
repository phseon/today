package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.controller.Action;
import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;


public class FaqUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인x
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		
		int faq_num = Integer.parseInt(request.getParameter("faq_num"));
		
		
		FaqDAO dao = FaqDAO.getInstance();
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//수정전 데이터 호출
		
		if(user_auth != 1) {//로그인 회원번호=!작성자
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//일치
		FaqVO faq = new FaqVO();
		
		faq.setFaq_num(faq_num);
		faq.setFaq_title(request.getParameter("faq_title"));
		faq.setFaq_content(request.getParameter("faq_content"));
		faq.setFaq_type(request.getParameter("faq_type"));
		
		dao.updateFaq(faq);
		return "redirect:/qna/faqDetail.do?faq_num="+faq_num;
		
	}

}
