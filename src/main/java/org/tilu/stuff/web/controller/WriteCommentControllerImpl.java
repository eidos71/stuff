package org.tilu.stuff.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.businessdelegate.WebBussinessDelegateImpl;
@Controller
public class WriteCommentControllerImpl implements WriteCommentController {
	@Resource
	private WebBussinessDelegate webBussinessDelegate;

	@Required
	public void setWebBussinessDelegate(WebBussinessDelegate webBussinessDelegate) {
		this.webBussinessDelegate = webBussinessDelegate;
	}


	@Resource
	private CommentFormBean commentFormBean;


	@RequestMapping("/writecomment")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Transform the data in something useful
		if (webBussinessDelegate==null){
			
			System.out.println("webBussinessDelegate is null");
			webBussinessDelegate= new WebBussinessDelegateImpl();
		}
		Map<String, Object> model = new HashMap();	
		System.out.println(this.webBussinessDelegate.createCommentPolicy(commentFormBean) );
		return new ModelAndView("successwriteform", model);
	}
	
}
