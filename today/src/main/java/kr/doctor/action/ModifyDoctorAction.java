package kr.doctor.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.doctor.dao.DoctorDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;

public class ModifyDoctorAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 X
			return "redirect:/member/loginForm.do";
		}
		
		//auth 관리자 여부 확인
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=1) {
			return "redirect:/main/main.do";
		}
		
		//로그인 O 관리자여부 확인 후
		MultipartRequest multi = FileUtil.createFile(request);
		String imgsrc = multi.getFilesystemName("d_imgsrc");
		
		int d_num = Integer.parseInt(multi.getParameter("d_num"));
		DoctorDAO dao = DoctorDAO.getInstance();
		MemberVO db_doctor = dao.getDoctorDetail(d_num);
		
		//자바빈 생성 후 데이터 전송
		MemberVO doctor = new MemberVO();
		doctor.setM_num(d_num);
		doctor.setContent(multi.getParameter("d_content"));
		doctor.setImgsrc(imgsrc);
		
		dao.updateDoctor(doctor);
		
		//기존 이미지 삭제
		if(imgsrc!=null) {
			FileUtil.removeFile(request, db_doctor.getImgsrc());
		}
		
		return "/WEB-INF/views/doctor/modifyDoctor.jsp";
	}

}
