package org.tilu.stuff.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.beans.CommentFormBeanImpl;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.tools.StatusEnum;
import org.tilu.stuff.tools.ViewName;
@Controller
public class WriteCommentControllerImpl implements WriteCommentController {
	private final static Logger logger = LoggerFactory.getLogger(WriteCommentControllerImpl.class);
	final static String  COMMENT_FORM = "inputComment";
	private static final String RANT_ID = "commentId";
	@Resource
	private WebBussinessDelegate webBussinessDelegate;

	public WriteCommentControllerImpl(WebBussinessDelegate webBussinessDelegate) {
		this.webBussinessDelegate = webBussinessDelegate;
	}
	public WriteCommentControllerImpl(){
		
	}
	@Resource
	private Validator validator;
	@Required
	public void setWebBussinessDelegate(WebBussinessDelegate webBussinessDelegate) {
		this.webBussinessDelegate = webBussinessDelegate;
	}



	private CommentFormBean commentFormBean;

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

	@RequestMapping(value="/writecomment.do",method=RequestMethod.POST) 
	@Override
	public ModelAndView handleRequest( @Valid CommentFormBean commentFormBean, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) throws Exception {
		//Transform the data in something useful
		
		Map<String, Object> model = new HashMap();	
		String viewname = null;
		// if attribute is not null or empty.
		if (request.getParameter(COMMENT_FORM) !=null && !request.getParameter(COMMENT_FORM).isEmpty()){
			
			if (logger.isDebugEnabled() ) logger.debug((String) request.getParameter(COMMENT_FORM) );
			commentFormBean=new CommentFormBeanImpl();
			 if (result.hasErrors()) {
				 System.out.println("result has errors()"); 
			 }
		}else{
			logger.error("inputComment parameter is coming null");
			throw new IllegalArgumentException("inputComment is null");
		}
			
		StatusEnum status = this.webBussinessDelegate.createCommentPolicy(commentFormBean);
		if (status.equals(StatusEnum.SUCCESS) ){
			viewname=ViewName.successwriteform.name();
	
		}else if (status.equals(StatusEnum.FAILURE) ) {
			viewname=ViewName.cancelwriteform.name();
		}
		if (logger.isInfoEnabled()){
			logger.info("viewname value is {} and status is {}", viewname, status);
		}
		return new ModelAndView(viewname, model);
	}
	@RequestMapping(value="/deletecomment.do",method=RequestMethod.GET) 
	@Override
	public ModelAndView deleteRequest (HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		if (request.getParameter(RANT_ID) !=null && !request.getParameter(RANT_ID).isEmpty()){
			
			if (logger.isDebugEnabled() ) logger.debug((String) request.getParameter(RANT_ID) );
			//lets create here the mockid.
			commentFormBean=new CommentFormBeanImpl();
		}else{
			logger.error("inputComment parameter is coming null");
			throw new IllegalArgumentException("inputComment is null");
		}
		StatusEnum status = this.webBussinessDelegate.deleteCommentPolicy(commentFormBean);
		String viewname=null;
		if (status.equals(StatusEnum.SUCCESS) ){
			viewname=ViewName.successdeleteform.name();
	
		}else if (status.equals(StatusEnum.FAILURE) ) {
			viewname=ViewName.canceldeleteform.name();
		}
		if (logger.isInfoEnabled()){
			logger.info("viewname value is {} and status is {}", viewname, status);
		}
		
		Map<String, Object> model = new HashMap();	
		return new ModelAndView(viewname, model);
		
	}
	
}
