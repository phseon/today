package kr.myinfo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.member.vo.MemberVO;
import kr.myinfo.dao.MyInfoDAO;
import kr.util.FileUtil;

public class DeleteMyPhotoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		/*
		// 현재 로그인 세션 정보 저장
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		// 현재 로그인 세션
		if(user_num==null) {
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우  
		*/
		
		int user_num = 6;  //삭제하기
			MyInfoDAO dao = MyInfoDAO.getInstance();
			
			//로그인  유저 정보
			MemberVO member = dao.getMemberInfo(user_num);
			
			dao.deleteMyPhoto(user_num);
			// session.setAttribute("user_photo", null);
			
			//이전 프로필 이미지 삭제
			FileUtil.removeFile(request, member.getImgsrc());
			
			mapAjax.put("result", "success");
		/*
		}
		*/
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";

	}

	
}
