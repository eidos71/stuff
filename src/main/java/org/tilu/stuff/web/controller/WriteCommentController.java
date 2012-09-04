package org.tilu.stuff.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.beans.CommentFormBeanImpl;

public interface WriteCommentController {

	/**
	 * 
	 * @param commentFormBean
	 * @param request
	 * @param response
	 * @param result
	 * @return
	 * @throws Exception
	 */
	ModelAndView handleRequest(CommentFormBean commentFormBean,
			HttpServletRequest request, HttpServletResponse response,
			BindingResult result) throws Exception;

	ModelAndView deleteRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
