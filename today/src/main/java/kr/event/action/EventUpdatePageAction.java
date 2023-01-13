package kr.event.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventUpdatePageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//eventUpdateFormPage.jsp에서 호출
		
		HttpSession session = request.getSession(); 
		 Integer user_num = (Integer)session.getAttribute("user_num"); 
		 if(user_num == null) { 
		 return "redirect:/event/callLoginForm.do";
		 } 
		 Integer user_auth = (Integer)session.getAttribute("user_auth");

		if(user_auth != 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			request.setAttribute("accessMsg", "이벤트 글작성 권한이 없습니다.");
			request.setAttribute("accessUrl", "/WEB-INF/views/event/eventMainPage.jsp");
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		int e_num = Integer.parseInt(multi.getParameter("e_num"));
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String e_imgsrc = multi.getFilesystemName("e_imgsrc");
		String e_thumb = multi.getFilesystemName("e_thumb");
		
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(e_num);
		if(user_auth != 1) {
			FileUtil.removeFile(request, e_imgsrc);//업로드된 파일이 있으면 파일 삭제
			FileUtil.removeFile(request, e_thumb);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		EventVO event = new EventVO();
		event.setE_num(e_num);
		event.setE_title(multi.getParameter("e_title"));
		event.setE_start(multi.getParameter("e_startdate"));
		event.setE_end(multi.getParameter("e_enddate"));
		event.setE_content(multi.getParameter("e_content"));
		event.setE_imgsrc(e_imgsrc);
		event.setE_thumb(e_thumb);
		event.setE_resbtn(multi.getParameter("e_rcheck"));
		
		dao.updateEvent(event);
		
		if(e_imgsrc != null) {
			FileUtil.removeFile(request, db_event.getE_imgsrc());
		}else if(e_thumb != null) {
			FileUtil.removeFile(request, db_event.getE_thumb());
		}
		
		
		//수정할 정보 모두 eventVO에 담고 다시 get방식으로 eventDetail.do 호출
		return "redirect:/event/eventDetail.do?e_num=" + e_num + "&pageNum=" + pageNum;
	}

}
