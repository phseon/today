package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.StringUtil;

public class EventUpdateFormPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//eventDetailPage.jsp에서 수정버튼 누를 시 호출, get방식으로 이벤트 글 번호 받아옴

		//권한체크 나중에 세션에서 받아오기
		int auth = 1;
		request.setAttribute("auth", auth);
		
		//받아온 이벤트 글 번호 저장
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		
		//저장한 글 번호로 수정할 이벤트 호출해서 eventVO에 정보 저장
		EventDAO dao = EventDAO.getInstance();
		EventVO event = dao.getEvent(e_num);
		
		//제목에 큰따옴표 변환
		event.setE_title(StringUtil.parseQuot(event.getE_title()));
		
		//불러온 이벤트 글 정보 request에 저장
		request.setAttribute("event", event);
		
		//eventUpdateFromPage.jsp 호출해서 저장되어 있던 내용 수정 폼에 표시하게 정보 전달
		return "/WEB-INF/views/event/eventUpdateFormPage.jsp";
	}

}
