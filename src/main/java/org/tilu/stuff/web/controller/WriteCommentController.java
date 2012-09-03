package org.tilu.stuff.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface WriteCommentController {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public  ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteRequest (HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
