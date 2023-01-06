package kr.event.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventDeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();
		
		/*
		HttpSession session = request.getSession();
		Integer m_num = (Integer)session.getAttribute("m_num");
		if(m_num == null) { //로그인이 안된경우
			mapAjax.put("result", "logout");
		}else{
		
		}
		*/
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		int auth = 1;
		request.setAttribute("auth", auth);
		
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(e_num);
		String e_imgType = request.getParameter("e_imgType");
		System.out.println(request.getParameter("e_imgType"));
		if(auth != 1) {
			mapAjax.put("result", "wrongAccess");
		}else {
			dao.deleteFile(e_num, e_imgType);
			if(e_imgType.equals("e_thumb")) {
				FileUtil.removeFile(request, db_event.getE_thumb());
				System.out.println("asdfasd");
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
