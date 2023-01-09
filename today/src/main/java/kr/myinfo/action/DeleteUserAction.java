package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.myinfo.dao.MyInfoDAO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.jsp";
		}
		
		// 로그인 중일 때
		request.setCharacterEncoding("utf-8");
		
		// 폼에서 입력한 데이터 변수로 저장
		String form_id = request.getParameter("form_id");
		String form_pwd = request.getParameter("form_pwd");
		
		// 로그인한 아이디 변수로 저장
		String user_id = (String)session.getAttribute("user_id");
		
		// db에 내가 작성한 id의 데이터가 있는지 확인
		MyInfoDAO dao = MyInfoDAO.getInstance();
		MemberVO member = dao.checkMemberExist(form_id);
		boolean check = false;
		
		// db에 내 정보가 있고, 내가 작성한 id와 현재 내 id가 일치하다면
		if(member!=null && form_id.equals(user_id)){
			// 비밀번호 일치하는지 확인하기
			check = member.isCheckedPassword(form_pwd);
		}
		
		
		if(check) { // 비밀번호까지 일치하다면
			dao.deleteMemberInfo(user_num);
			
			// 프로필사진 삭제
			FileUtil.removeFile(request, member.getImgsrc());
			session.invalidate();
		}
		
		
		request.setAttribute("check", check);
		

		return "/WEB-INF/views/myinfo/deleteUser.jsp";
	}

}
