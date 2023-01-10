package kr.event.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventDeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();
		
		 HttpSession session = request.getSession(); 
		 Integer user_num = (Integer)session.getAttribute("user_num"); 
		 if(user_num == null) { 
		 return "redirect:/event/callLoginForm.do";
		 } 
		 Integer user_auth = (Integer)session.getAttribute("user_auth");

		if(user_auth != 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			request.setAttribute("accessMsg", "파일 삭제 권한이 없습니다.");
			request.setAttribute("accessUrl", "/WEB-INF/views/event/eventMainPage.jsp");
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(e_num);
		String e_imgType = request.getParameter("e_imgType");
		if(user_auth != 1) {
			mapAjax.put("result", "wrongAccess");
		}else {
			dao.deleteFile(e_num, e_imgType);
			if(e_imgType.equals("e_thumb")) {
				FileUtil.removeFile(request, db_event.getE_thumb());
			}else if(e_imgType.equals("e_imgsrc") ) {
				FileUtil.removeFile(request, db_event.getE_imgsrc());
			}
			
			mapAjax.put("result", "success");
		}
		
		//json 데이터생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
