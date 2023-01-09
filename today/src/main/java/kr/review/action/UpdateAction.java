package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.reservation.vo.ReservationVO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MultipartRequest multi = 
				            FileUtil.createFile(request);
		int m_num = Integer.parseInt(
				          multi.getParameter("m_num"));
		int r_num = Integer.parseInt(
				multi.getParameter("r_num"));
//		String filename = multi.getFilesystemName("filename");
		
		ReviewDAO dao = ReviewDAO.getInstance();
		//수정전 데이터 호출
		ReviewVO db_review = dao.getReview(r_num);
//		ReservationVO db_rez = dao.getRevInfo(m_num, r_num);
		if(user_num != db_review.getR_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
//			FileUtil.removeFile(request, filename);//업로드된 파일이 있으면 파일 삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		ReservationVO rez = new ReservationVO();
		ReviewVO review = new ReviewVO();
		rez.setM_num(m_num);
		review.setR_num(r_num);
		review.setR_content(multi.getParameter("R_content"));
		
		dao.updateReview(review);
//		dao.getRevInfo(m_num, r_num);
		
//		if(filename!=null) {
//			//새파일로 교체할 때 원래 파일 제거
//			FileUtil.removeFile(request, 
//					             db_board.getFilename());
//		}
		
		return "redirect:/reivew/detail.do?r_num="+r_num;
	}

}




