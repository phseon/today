package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class FaqUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		//일반회원인 경우
		if(user_auth ==2) {// 1:의사 2:일반
			return "/WEB-INF/views/common/notice.jsp";
		}
	
	
		//글번호 반환
		int faq_num = Integer.parseInt(
						request.getParameter("faq_num"));

		FaqDAO dao = FaqDAO.getInstance();
		FaqVO faq = dao.getFaq(faq_num);
		if(user_num!=faq.getM_num()) {
			return "/WEB-INF/views/common/notice.jsp";			
		}
				
		faq.setFaq_title(StringUtil.parseQuot(faq.getFaq_title()));
				
		request.setAttribute("faq", faq);

		return "/WEB-INF/views/faq/faqupdateform.jsp";
	}

}
