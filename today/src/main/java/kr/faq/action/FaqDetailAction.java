package kr.faq.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class FaqDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int faq_num = Integer.parseInt(request.getParameter("faq_num"));

		FaqDAO dao = FaqDAO.getInstance();
		FaqVO faq = dao.getFaq(faq_num);
		
		//HTML 태그를 허용하지 않음
		faq.setFaq_title(StringUtil.useNoHtml(faq.getFaq_title()));
		faq.setFaq_content(StringUtil.useBrNoHtml(faq.getFaq_content()));
		
		request.setAttribute("faq", faq);
		
		
		return "/WEB-INF/views/faq/faqdetail.jsp";
		
		
		
	}
	

}
