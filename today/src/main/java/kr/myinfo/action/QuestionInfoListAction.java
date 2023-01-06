package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class QuestionInfoListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/WEB-INF/views/myinfo/questionInfoList.jsp";
	}

}
