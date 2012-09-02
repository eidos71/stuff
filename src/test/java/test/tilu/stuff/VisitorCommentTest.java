package test.tilu.stuff;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;


import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.tools.StatusEnum;
import org.tilu.stuff.web.controller.WriteCommentController;
import org.tilu.stuff.web.controller.WriteCommentControllerImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/runVisitorComments-ctx.xml" })
public class VisitorCommentTest {

	private final static Logger logger = LoggerFactory.getLogger(VisitorCommentTest.class);
	private WriteCommentController writeController;
	@Resource
	private WebBussinessDelegate webBussinessDelegate;
	@Autowired
	ApplicationContext context;
	@Resource
	private Mockery mockery ;
	@Resource
	private CommentFormBean commentFormBean;
	@Before
	public void setUp() throws Exception {
		if (commentFormBean==null)
			logger.debug("is null commentFormBean");
		if (webBussinessDelegate==null)
			logger.debug("webBussinessDelegate s null");
		writeController= new WriteCommentControllerImpl(webBussinessDelegate);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testVisitorLeavesCommentAndFails() throws Exception {
		
		
		//controller is sent and information passed
		//Bean is injected with the information
		//Process ends.
		mockery.checking(new Expectations() {
			{
				//Visitor is logged and mocked.
				oneOf(webBussinessDelegate).createCommentPolicy(with(any(CommentFormBean.class))); will(returnValue(StatusEnum.FAILURE));
				
				
			}
		});
		ModelAndView mav= writeController.handleRequest(new MockHttpServletRequest(), new MockHttpServletResponse() );
		assertTrue("The cancelwriteform view should be returned.",
				"cancelwriteform".equals(mav.getViewName()));
		
	}
	@Test
	public void testVisitorLeavesComment() throws Exception {
		
		
		//controller is sent and information passed
		//Bean is injected with the information
		//Process ends.
		mockery.checking(new Expectations() {
			{
				//Visitor is logged and mocked.
				oneOf(webBussinessDelegate).createCommentPolicy(with(any(CommentFormBean.class)));will(returnValue(StatusEnum.SUCCESS));
				
			}
		});
		
		ModelAndView mav= writeController.handleRequest(new MockHttpServletRequest(), new MockHttpServletResponse() );
		assertTrue("The create view should be returned.",
				"successwriteform".equals(mav.getViewName()));
		
	}

}
