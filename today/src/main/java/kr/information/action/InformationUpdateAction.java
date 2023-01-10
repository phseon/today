package kr.information.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.information.dao.InformationDAO;
import kr.information.vo.InformationVO;

public class InformationUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		int info_num = Integer.parseInt(request.getParameter("info_num"));
		
		InformationDAO dao = InformationDAO.getInstance();
		//수정 전 데이터 호출
		InformationVO db_info = dao.getinfodetail(info_num);
		if(user_num != db_info.getM_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치하는 경우
		InformationVO info = new InformationVO();
		
		info.setInfo_num(info_num);
		info.setInfo_title(request.getParameter("title"));
		info.setInfo_content(request.getParameter("content"));
		info.setM_num(db_info.getM_num());
		
		dao.updateInfo(info);
		return "redirect:/information/detail.do?info_num="+info_num;
	}

}
