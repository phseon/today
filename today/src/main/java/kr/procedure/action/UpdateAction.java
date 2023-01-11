package kr.procedure.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.procedure.dao.ProcedureDAO;
import kr.procedure.vo.ProcedureVO;
import kr.util.FileUtil;
 
public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null || user_auth!= 1) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int p_num = Integer.parseInt(multi.getParameter("p_num"));
		String imgsrc = multi.getFilesystemName("imgsrc");
		
		ProcedureDAO dao = ProcedureDAO.getInstance();
		ProcedureVO pro = dao.getProcedure(p_num);
		if(user_num != pro.getM_num()) {
			return "/WEB-INF/views/procedure/notice.jsp";
		}
		ProcedureVO pro1 = new ProcedureVO();
		pro1.setP_num(p_num);
		pro1.setP_title(multi.getParameter("title"));
		pro1.setP_content(multi.getParameter("content"));
		pro1.setP_imgsrc(imgsrc);
		
		dao.updateProcedure(pro1);
		
		if(imgsrc !=null) {
			//원래 파일 삭제
			FileUtil.removeFile(request, pro.getP_imgsrc());
		}
		return "redirect:/procedure/detail.do?p_num="+p_num;
	}

}
