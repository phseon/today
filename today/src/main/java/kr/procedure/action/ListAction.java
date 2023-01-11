package kr.procedure.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum ="1";
		
		ProcedureDAO dao = ProcedureDAO.getInstance();
		int count = dao.getCount();
		
		PagingUtil page = new PagingUtil(
				Integer.parseInt(pageNum), count, count, 1, null);
		
		List<ProcedureVO> list = null;
		if(count>0) {
			list = dao.select(page.getStartRow(), page.getEndRow());
		}
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		
		return "/WEB-INF/views/procedure/list.jsp";
	}

}
