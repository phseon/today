package kr.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.PagingUtil;

public class EventMainPageAction implements	Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//navBar에서 이벤트 클릭 시 호출됨, eventMainPage.jsp에서 검색 시 호출
		
		//페이지처리 처음 호출될 때 pageNum은 null이므로 1로 설정, 다음 페이지 누를 때마다 호출 pageNum 증가
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		//eventMainPage 검색시 호출될 때 받아온 keyfiled, keyword request에 저장
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		//dao에 받아온 keyfield, keyword 넘겨주고 페이지 개수 받아오기
		EventDAO dao = EventDAO.getInstance();
		dao.updateCalDate();
		int count = dao.getEventCount(keyfield, keyword);
		
		//페이지 처리
		
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 6, 1, "eventPage.do");
		List<EventVO> list = null;
		if(count > 0) {
			list = dao.getListEvent(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		int closecnt = 3;
		List<EventVO> topList = dao.getCloseEvent(closecnt);
		
		//request에 페이지 개수, 받아온 페이지 내 이벤트 리스트, 페이지수 저장
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("topList", topList);
		request.setAttribute("page", page.getPage());
		
		request.setAttribute("pageNum", pageNum);
		
		
		//다시 eventMainPage.jsp 호출
		return "/WEB-INF/views/event/eventMainPage.jsp";
	}

}
