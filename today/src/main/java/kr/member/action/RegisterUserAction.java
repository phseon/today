package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈 생성, 전송된 데이터 저장
		MemberVO member = new MemberVO();
		member.setAuth(Integer.parseInt(request.getParameter("chk_info")));
		member.setId(request.getParameter("id"));
		member.setName(request.getParameter("name"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
		
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}
	
}
