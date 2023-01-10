package kr.information.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.information.dao.InformationDAO;
import kr.information.vo.InformationVO;
import kr.util.StringUtil;

public class InformationDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int info_num = Integer.parseInt(request.getParameter("info_num"));
		
		InformationDAO dao = InformationDAO.getInstance();
		InformationVO info = dao.getinfodetail(info_num);
		
		//HTML 태그를 허용하지 않음
		info.setInfo_title(StringUtil.useNoHtml(info.getInfo_title()));
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		info.setInfo_content(StringUtil.useBrNoHtml(info.getInfo_content()));
		
		request.setAttribute("info", info);
		
		return "/WEB-INF/views/information/informationDetail.jsp";
	}

}
