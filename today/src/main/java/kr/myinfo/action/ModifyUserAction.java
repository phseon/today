package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.myinfo.dao.MyInfoDAO;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/login.do";
		} 
		
		// 로그인시
		request.setCharacterEncoding("utf-8");
		
		// form에 입력한 데이터들을 membervo에 저장하기
		MemberVO member = new MemberVO();
		
		member.setM_num(user_num);
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));
		
		MyInfoDAO dao = MyInfoDAO.getInstance();
		dao.updateMemberInfo(member);
		
		return "/WEB-INF/views/myinfo/modifyUser.jsp";
	}

}
