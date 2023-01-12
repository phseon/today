package kr.faq.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.faq.dao.FaqDAO;
import kr.faq.vo.FaqVO;
import kr.util.PagingUtil;

public class FaqListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");

		FaqDAO dao = FaqDAO.getInstance();
		int count = dao.getFaqCount(keyfield, keyword);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 10, 10, "list.do"); 
		
		List<FaqVO> list = null;
		if(count >0) {
			list = dao.getListFaq(page.getStartRow(), page.getEndRow(), keyfield,keyword);
		}
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());
		
		
		return "/WEB-INF/views/faq/list.jsp";
		

	}

}
