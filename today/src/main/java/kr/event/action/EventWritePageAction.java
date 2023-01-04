package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventWritePageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		 * HttpSession session = request.getSession(); 
		 * Integer m_num = (Integer)session.getAttribute("m_num"); 
		 * if(m_num == null) { 
		 * return "redirect:/member/loginForm.do";
		 * } 
		 * Integer auth = (Integer)session.getAttribute("auth");
		 */
		//일단 받아오기 전까지 데이터 강제 세팅 나중에 해제
		int auth = 1;
		if(auth != 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자 권한 로그인인 경우
		MultipartRequest multi = FileUtil.createFile(request);
		EventVO event = new EventVO();
		event.setE_title(multi.getParameter("e_title"));
		event.setE_start(multi.getParameter("e_startdate"));
		event.setE_end(multi.getParameter("e_enddate"));		
		event.setE_content(multi.getParameter("e_content"));
		event.setE_imgsrc(multi.getFilesystemName("e_imgsrc"));
		//일단은 auth값 1로 처리, 나중에 m_num 데이터 받아오기
		//event.setM_num(m_num)
		event.setM_num(auth);
		
		EventDAO dao = EventDAO.getInstance();
		dao.insertEvent(event);
		
		return "/WEB-INF/views/event/eventWritePage.jsp";
	}

}
