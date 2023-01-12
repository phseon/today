package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;
import kr.util.FileUtil;

public class FaqWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 안 함
			return "redirect:/member/loginForm.do";
		}
		
		//auth 관리자 여부 확인
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=1) {//관리자가 아닐 때
			return "redirect:/main/main.do";
		}
		
		MultipartRequest multi =  FileUtil.createFile(request);
		FaqVO faq = new FaqVO();
		faq.setFaq_title(multi.getParameter("faq_title"));
		faq.setFaq_content(multi.getParameter("faq_content"));
		faq.setFaq_type(multi.getParameter("faq_type"));
		faq.setM_num(user_num);
		
		FaqDAO dao = FaqDAO.getInstance();
		dao.insertFaq(faq);
		
		
		return "/WEB-INF/views/faq/faqwrite.jsp";
		
		
	}

}
