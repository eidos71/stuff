package test.tilu.stuff;

import static org.junit.Assert.*;


import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;


import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.beans.CommentFormBeanImpl;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.tools.StatusEnum;
import org.tilu.stuff.web.controller.WriteCommentController;
import org.tilu.stuff.web.controller.WriteCommentControllerImpl;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/runVisitorComments-ctx.xml" })
public class VisitorCommentTest {
	
	final static String  COMMENT_FORM = "inputComment";
	private final static Logger logger = LoggerFactory.getLogger(VisitorCommentTest.class);
	private WriteCommentController writeController;
	@Resource
	private WebBussinessDelegate webBussinessDelegate;
	@Autowired
	ApplicationContext context;
	@Inject
	private  Mockery mockContext ;
	@Resource
	private CommentFormBean commentFormBean;
	private MockHttpServletRequest mockHttpServletRequest;

	@Mock
	private BindingResult mockBindingResult;
	@Mock
	private CommentFormBeanImpl _commentFormBean;
	@Mock
	private WebBussinessDelegate _webBussinessDelegate;
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	@Before
	public void setUp() throws Exception {
		
		mockContext= context.getBean("mockContext",Mockery.class);
		MockitoAnnotations.initMocks(this);
		if (mockContext==null)
			logger.debug("is null mockContext");
		if (commentFormBean==null)
			logger.debug("is null commentFormBean");
		if (webBussinessDelegate==null)
			logger.debug("webBussinessDelegate s null");
		writeController= new WriteCommentControllerImpl(webBussinessDelegate);
		mockHttpServletRequest= new MockHttpServletRequest();
	}

	@After
	public void tearDown() throws Exception {
	}

	@BeforeClass
	public static void  setUpBeforeClass() throws Exception {
	
		
	}
	@Ignore
	@Test
	public void testVisitorInvalidResult() throws Exception {

		mockHttpServletRequest.setMethod("POST");
		MockitoAnnotations.initMocks(this);
		//Mockito.when(mockBindingResult.hasErrors()).thenReturn(false);
		Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);
		String mockString="Lore Ipsum quoonquoe value";
		mockHttpServletRequest.addParameter(COMMENT_FORM, mockString);
		Mockito.when(_webBussinessDelegate.createCommentPolicy(_commentFormBean) ).thenReturn(StatusEnum.SUCCESS);
		
		ModelAndView mav= writeController.handleRequest(_commentFormBean,
				mockHttpServletRequest, new MockHttpServletResponse(),mockBindingResult );
		
		logger.info(mav.getViewName() );
	
	}

	@Test 
	public void testVisitorCommentsEmpty() throws Exception{
		//controller is sent and information passed
		//Bean is injected with the information
		//Process ends.
		mockContext.states( UUID.randomUUID().toString() );
		mockContext.checking(new Expectations() {
			{
				//Visitor is logged and mocked.
				exactly(1).of(webBussinessDelegate).createCommentPolicy(with(any(CommentFormBean.class))); will(returnValue(StatusEnum.SUCCESS));
				
			}
		});
		//we add a parameter.
		mockHttpServletRequest.setMethod("POST");
		String mockString="Este es un comentario que ha sido enviado a por el tema";
		mockHttpServletRequest.addParameter(COMMENT_FORM, mockString);
		ModelAndView mav= writeController.handleRequest(commentFormBean,
					mockHttpServletRequest, new MockHttpServletResponse(),mockBindingResult );
		assertTrue("The cancelwriteform view should be returned.",
				"cancelwriteform".equals(mav.getViewName()));
			
	}
	@Test (expected = IllegalArgumentException.class)  
	public void testVistiorLeavesCommentNullRequest() throws Exception{
		//controller is sent and information passed
		//Bean is injected with the information
		//Process ends.
		mockContext.states( UUID.randomUUID().toString() );
		mockContext.checking(new Expectations() {
			{
				//Visitor is logged and mocked.
				exactly(1).of(webBussinessDelegate).createCommentPolicy(with(any(CommentFormBean.class))); will(returnValue(StatusEnum.SUCCESS));
				
			}
		});
		//we add a parameter.
		mockHttpServletRequest.setMethod("POST");
		String mockNull=null;
		mockHttpServletRequest.addParameter(COMMENT_FORM, mockNull);
		ModelAndView mav= writeController.handleRequest(commentFormBean,
				mockHttpServletRequest, new MockHttpServletResponse(),mockBindingResult );
		assertTrue("The successwriteform view should be returned.",
				"successwriteform".equals(mav.getViewName()));
	}
	@Test
	public void testVisitorLeavesComment() throws Exception {
		
		mockContext.states( UUID.randomUUID().toString() );
		//controller is sent and information passed
		//Bean is injected with the information
		//Process ends.
		Expectations success= new Expectations() {
			{
				//Visitor is logged and mocked.
				exactly(1).of(webBussinessDelegate).createCommentPolicy(with(any(CommentFormBean.class)));
				will(returnValue(StatusEnum.SUCCESS));
				
			}
		};
	
		mockContext.checking(success);
		logger.info(mockContext.toString() );
		//we add a parameter.

		mockHttpServletRequest.setMethod("POST");
		String mockString="Este es un comentario que ha sido enviado a por el tema";
		mockHttpServletRequest.addParameter(COMMENT_FORM, mockString );
		
		ModelAndView mav= writeController.handleRequest(commentFormBean,
				mockHttpServletRequest, new MockHttpServletResponse(),mockBindingResult );
		assertTrue("The successwriteform view should be returned.",
				"successwriteform".equals(mav.getViewName()));
		
	}
	@Ignore
	@Test public void testVisitorLeavesCommentProcessFails() throws Exception {
		//The mockery is keeping the status and giving a big error on ll
		final States initializationState= mockContext.states( "failure-state" ).startsAs( "failurestate" );
		//controller is sent and information passed
		//Bean is injected with the information
		//Process ends.
		Expectations failure= new Expectations() {
			{
				//Visitor is logged and mocked.
			
				exactly(1).of(webBussinessDelegate).createCommentPolicy(with(any(CommentFormBean.class)));
				when( initializationState.is( "failurestate" ) );
				will(returnValue(StatusEnum.FAILURE));
				
			}
		};
		
		
		mockContext.checking(failure);
		logger.info(mockContext.toString() );
		
		//we add a parameter.
		mockHttpServletRequest.setMethod("POST");
		String mockNull="Este es un comentario que ha sido enviado y por algún motivo se detendra en su proceso.";
		mockHttpServletRequest.addParameter(COMMENT_FORM, mockNull);
		ModelAndView mav= writeController.handleRequest(commentFormBean,
				mockHttpServletRequest, new MockHttpServletResponse(),mockBindingResult );
		assertTrue("The cancelwriteform view should be returned.",
				"cancelwriteform".equals(mav.getViewName()));
		
	}


}
