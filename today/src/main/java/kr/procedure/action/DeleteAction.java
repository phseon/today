package kr.procedure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null || user_auth!= 1) {
			return "redirect:/member/loginForm.do";
		}
		//입력한 패스워드
		String passwd = request.getParameter("passwd");
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		ProcedureDAO dao = ProcedureDAO.getInstance();
		ProcedureVO pro = dao.getProcedure(p_num);
		//사용자 패스워드
		String pwd = dao.pwdProcedure(user_num);
		if(passwd.equals(pwd)) {
			dao.deleteProcedure(p_num);
			FileUtil.removeFile(request, pro.getP_imgsrc());
			return "redirect:/procedure/list.do";
		}else {
			return "/WEB-INF/views/procedure/notice.jsp";
		}

	}

}
