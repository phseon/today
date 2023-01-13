package kr.doctor.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.doctor.dao.DoctorDAO;
import kr.member.vo.MemberVO;
import kr.util.PagingUtil;

public class DoctorListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//ajax 방식으로 페이지 처리
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
				
		DoctorDAO dao = DoctorDAO.getInstance();
		int count = dao.getDoctorCount();
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),
												count,3,10,"doctorList.do");
				
		List<MemberVO> list = null;
		if(count>0) {
			list = dao.getDoctor(page.getStartRow(), page.getEndRow());
		}
		
		request.setAttribute("count", count);
		request.setAttribute("pageNum", page.getPage());
		request.setAttribute("list", list);

		return "/WEB-INF/views/doctor/doctorList.jsp";
	}

}
