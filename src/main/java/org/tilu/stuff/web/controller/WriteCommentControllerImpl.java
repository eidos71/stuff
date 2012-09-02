package org.tilu.stuff.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.beans.CommentFormBeanImpl;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.tools.StatusEnum;
import org.tilu.stuff.tools.ViewName;
@Controller
public class WriteCommentControllerImpl implements WriteCommentController {
	private final static Logger logger = LoggerFactory.getLogger(WriteCommentControllerImpl.class);
	@Autowired
	private WebBussinessDelegate webBussinessDelegate;

	public WriteCommentControllerImpl(WebBussinessDelegate webBussinessDelegate) {
		this.webBussinessDelegate = webBussinessDelegate;
	}
	public WriteCommentControllerImpl(){
		
	}

	@Required
	public void setWebBussinessDelegate(WebBussinessDelegate webBussinessDelegate) {
		this.webBussinessDelegate = webBussinessDelegate;
	}


	@Resource
	private CommentFormBean commentFormBean;

	@Required
	public void setCommentFormBean(CommentFormBean commentFormBean) {
		this.commentFormBean = commentFormBean;
	}
	@RequestMapping("/writecomment")
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Transform the data in something useful
		
		Map<String, Object> model = new HashMap();	
		String viewname = null;
		if (commentFormBean==null){
			commentFormBean=new CommentFormBeanImpl();
		}
			
		StatusEnum status = this.webBussinessDelegate.createCommentPolicy(commentFormBean);
		if (status.equals(StatusEnum.SUCCESS) ){
			viewname=ViewName.successwriteform.name();
	
		}else if (status.equals(StatusEnum.FAILURE) ) {
			viewname=ViewName.cancelwriteform.name();
		}
		if (logger.isInfoEnabled()){
			logger.info("viewname value is {}", viewname);
		}
		return new ModelAndView(viewname, model);
	}
		
	
}
