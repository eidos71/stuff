package test.tilu.stuff.web.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;

import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;
import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.tools.StatusEnum;
import org.tilu.stuff.web.controller.WriteCommentController;
import org.tilu.stuff.web.controller.WriteCommentControllerImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/runVisitorComments-ctx.xml" })
public class WriteCommentControllerTest {
	@Inject
	private  Mockery mockContext ;
	@Autowired
	private ApplicationContext applicationContext;
	private static final String RANT_ID = "commentId";
	private MockServletContext servletContext;
	private MockHttpServletResponse response;
	private MockHttpServletRequest request;

	private HandlerAdapter handlerAdapter;
	@Resource
	protected WebBussinessDelegate webBussinessDelegate;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@After
	public void tearDown() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest("GET", "/secure/view");
		response = new MockHttpServletResponse();
		servletContext = new MockServletContext();
		mockContext= applicationContext.getBean("mockContext",Mockery.class);
		//handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
	}
	@Test
	public void testWriteCommentControllerImplWebBussinessDelegate() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteCommentControllerImpl() {
		fail("Not yet implemented");
	}
	@Test
	public void testHandleRequest() throws Exception {
		Expectations success= new Expectations() {
			{
				//Visitor is logged and mocked.
			
				exactly(1).of(webBussinessDelegate).deleteCommentPolicy(with(any(CommentFormBean.class)));
				will(returnValue(StatusEnum.SUCCESS));
				
			}
		};
		mockContext.checking(success);
			String mockId="15";
			request.setContextPath("/writecomment.do");
	        request.setRequestURI("/writecomment.do");
	        request.addParameter(RANT_ID, mockId);
	        request.setMethod("get");
	        ModelMap model = new ModelMap();
	        WriteCommentController writ= new WriteCommentControllerImpl(webBussinessDelegate);
	        assertEquals( "successdeleteform", writ.deleteRequest(request, response).getViewName() );
	        
	}

}
