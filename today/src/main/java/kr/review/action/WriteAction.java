package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.reservation.dao.ReservationDAO;
import kr.reservation.vo.ReservationVO;
import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewVO;
import kr.util.FileUtil;

public class WriteAction implements Action{

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
		ReviewVO review = new ReviewVO();
		
		review.setR_content(multi.getParameter("r_content"));
		review.setR_imgsrc(multi.getFilesystemName("r_imgsrc"));
		review.setStar(Integer.parseInt(multi.getParameter("star")));
		
		ReviewDAO dao = ReviewDAO.getInstance();
		ReservationDAO rez_dao = ReservationDAO.getInstance();
		ReservationVO rez = rez_dao.getReservation(user_num);
		
		dao.insertReivew(review,rez.getRev_num());
		
		return "/WEB-INF/views/review/write.jsp";
	}

}
