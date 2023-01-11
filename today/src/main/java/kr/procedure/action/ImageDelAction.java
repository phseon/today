package kr.procedure.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;
import kr.util.FileUtil;

public class ImageDelAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String>mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num ==null || user_auth!=1) {
			mapAjax.put("result", "logout");
		}else {
			int p_num = Integer.parseInt(request.getParameter("p_num"));
			ProcedureDAO dao = ProcedureDAO.getInstance();
			ProcedureVO pro = dao.getProcedure(p_num);
			if(user_num != pro.getM_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.delteImage(p_num);
				FileUtil.removeFile(request, pro.getP_imgsrc());
				mapAjax.put("result", "success");
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/procedure/ajax_view.jsp";
	}

}
