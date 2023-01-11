package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.StringUtil;

public class EventDetailPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//eventMainPage.jsp에서 이벤트 글 클릭시 호출 get방식으로 이벤트 번호 전달받음
		//전달받은 이벤트 번호(e_num) 저장
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		//전달 받은 이벤트 번호로 해당 이벤트 정보 eventVO에 저장
		EventDAO dao = EventDAO.getInstance();
		EventVO event = dao.getEvent(e_num);
		
		//제목과 내용 html 사용 못하게 설정
		event.setE_title(StringUtil.useNoHtml(event.getE_title()));
		event.setE_content(StringUtil.useBrNoHtml(event.getE_content()));
		
		//eventVO에 저장한 정보 request에 담기
		request.setAttribute("event", event);
		request.setAttribute("pageNum", pageNum);
		
		//eventDetailPage.jsp 호출해서 저장된 데이터 넘겨주고 ui 호출
		return "/WEB-INF/views/event/eventDetailPage.jsp";
	}

}
