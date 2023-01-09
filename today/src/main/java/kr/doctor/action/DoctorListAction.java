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
		
		//페이지 번호 반환
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
				
		DoctorDAO dao = DoctorDAO.getInstance();
		int count = dao.getDoctorCount();
				
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),
												count,10,10,"doctorList.do");
				
		List<MemberVO> list = null;
		if(count>0) {
			list = dao.getDoctor(page.getStartRow(), page.getEndRow());
		}
				
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());

		return "/WEB-INF/views/doctor/doctorList.jsp";
	}

}
