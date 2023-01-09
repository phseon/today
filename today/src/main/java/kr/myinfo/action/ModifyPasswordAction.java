package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.myinfo.dao.MyInfoDAO;

public class ModifyPasswordAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.jsp";
		} 
		
		// 로그인된 경우
		request.setCharacterEncoding("utf-8");
		
		// 전송받은 데이터 저장하기
		String id = request.getParameter("id");
		String origin_passwd = request.getParameter("origin_passwd");
		String passwd = request.getParameter("passwd");
		
		// 현재 로그인한 아이디 변수에 저장
		String user_id = (String)session.getAttribute("user_id"); 
		
		MyInfoDAO dao = MyInfoDAO.getInstance();
		MemberVO member = dao.checkMemberExist(id);
		boolean check = false;
		
		// 현재 로그인아이디(user_id)와 내가 입력한 정보가 동일하고, db에 내가 입력한 id의 정보가 있다면
		if(member!=null && id.equals(user_id)) {
			check = member.isCheckedPassword(origin_passwd);
		}
		if(check) { // check까지 통과되면 비밀번호 변경하기
			dao.updateMemberPassword(passwd,user_num);
		}
		request.setAttribute("check", check);
		
		
		
		return "/WEB-INF/views/myinfo/modifyPassword.jsp";
	}

}
