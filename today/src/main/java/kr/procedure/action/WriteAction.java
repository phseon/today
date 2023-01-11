package kr.procedure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;
import kr.util.FileUtil;
import kr.util.StringUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션 넣기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null || user_auth!= 1) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		
		ProcedureVO pro = new ProcedureVO();
		pro.setP_title(multi.getParameter("title"));
		pro.setP_content(multi.getParameter("content"));
		pro.setP_imgsrc(multi.getFilesystemName("imgsrc"));
		
		pro.setM_num(user_num);
		pro.setP_content(StringUtil.useBrNoHtml(pro.getP_content()));
		ProcedureDAO dao = ProcedureDAO.getInstance();
		
		dao.insertProcedure(pro);
		
		return "/WEB-INF/views/procedure/write.jsp";
	}

}
