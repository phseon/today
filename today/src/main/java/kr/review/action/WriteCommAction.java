package kr.review.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.review.dao.ReviewDAO;
import kr.review.vo.ReviewCommVO;
import kr.review.vo.ReviewVO;
import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.reservation.vo.ReservationVO;

public class WriteCommAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = 
				            new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");

			MemberVO member = new MemberVO();
			ReviewCommVO comm = new ReviewCommVO();
			ReviewVO review = new ReviewVO();
			
			String com = request.getParameter("c_content");
			
			//현재 날짜를 sql에 사용하도록 변환
			Date date = new Date();

	        long timeInMilliSeconds = date.getTime();
	        java.sql.Date nowdate = new java.sql.Date(timeInMilliSeconds); 
			
			member.setM_num(user_num);//회원번호(댓글 작성자)
			comm.setC_content(request.getParameter("c_content"));
			review.setR_num(Integer.parseInt(
			         request.getParameter("r_num")));
			comm.setC_date(nowdate);
			
			ReviewDAO dao = ReviewDAO.getInstance();
			dao.insertCommReview(member,review,comm);
			
			
			mapAjax.put("result", "success");
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
