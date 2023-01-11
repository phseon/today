package kr.procedure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		ProcedureDAO dao = ProcedureDAO.getInstance();
		ProcedureVO pro = dao.getProcedure(p_num);
		
		pro.setP_title(StringUtil.useNoHtml(pro.getP_title()));
		pro.setP_content(StringUtil.useBrNoHtml(pro.getP_content()));
		
		request.setAttribute("pro", pro);
		return "/WEB-INF/views/procedure/detail.jsp";
	}

}
