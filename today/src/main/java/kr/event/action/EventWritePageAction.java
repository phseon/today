package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventWritePageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//eventWriteFormPage.jsp에서 등록 누르면 eventwrite_form 폼을 통해 데이터 전달 받음
		
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
		
		//관리자 권한 로그인인 경우
		//eventWriteForm의 eventwrite_form 폼에서 받아온 정보 eventVO에 담기
		MultipartRequest multi = FileUtil.createFile(request);
		EventVO event = new EventVO();
		event.setE_title(multi.getParameter("e_title"));
		event.setE_start(multi.getParameter("e_startdate"));
		event.setE_end(multi.getParameter("e_enddate"));		
		event.setE_content(multi.getParameter("e_content"));
		event.setE_imgsrc(multi.getFilesystemName("e_imgsrc"));
		event.setE_thumb(multi.getFilesystemName("e_thumb"));
		event.setE_resbtn(multi.getParameter("e_rcheck"));
		event.setM_num(user_num);
		
		//dao 호출해서 받아온 데이터 db에 저장
		EventDAO dao = EventDAO.getInstance();
		dao.insertEvent(event);
		
		//글 등록 완료 페이지로 이동
		return "/WEB-INF/views/event/eventWritePage.jsp";
	}

}
