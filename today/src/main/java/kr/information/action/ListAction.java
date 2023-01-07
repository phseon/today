package kr.information.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.information.dao.InformationDAO;
import kr.information.vo.InformationVO;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//페이지 번호 반환
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		InformationDAO dao = InformationDAO.getInstance();
		int count = dao.getCount();
		
		//페이지 처리
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 10, 10, "list.do"); 
		
		List<InformationVO> list = null;
		if(count>0) {
			list = dao.getListInfo(page.getStartRow(), page.getEndRow());//getlist : 전체리스트 반환,PagingUtil이 연산해줌, startrow가 1이면 endrow가 10, startrow가 2이면 endrow가 20
			
		}
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());
		
		
		return "/WEB-INF/views/information/list.jsp";
	}

}
