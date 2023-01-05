package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventUpdatePageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		/*
		 * Integer m_num = (Integer)session.getAttribute("m_num"); 
		 * if(m_num == null) {
		 * //로그인이 안된경우 
		 * return "redirect:/member/loginForm.do";
		 * }
		 */
		//권한 나중에 세션에서 받아오기
		int auth = 1;
		request.setAttribute("auth", auth);
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int e_num = Integer.parseInt(multi.getParameter("e_num"));
		
		String e_imgsrc = multi.getFilesystemName("e_imgsrc");
		
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(e_num);
		if(auth != 1) {
			FileUtil.removeFile(request, e_imgsrc);//업로드된 파일이 있으면 파일 삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		EventVO event = new EventVO();
		event.setE_num(e_num);
		event.setE_title(multi.getParameter("e_title"));
		event.setE_start(multi.getParameter("e_startdate"));
		event.setE_end(multi.getParameter("e_enddate"));
		event.setE_content(multi.getParameter("e_content"));
		event.setE_imgsrc(e_imgsrc);
		
		dao.updateEvent(event);
		
		if(e_imgsrc != null) {
			FileUtil.removeFile(request, db_event.getE_imgsrc());
		}
		
		
		
		return "redirect:/event/eventDetail.do?e_num=" + e_num;
	}

}
