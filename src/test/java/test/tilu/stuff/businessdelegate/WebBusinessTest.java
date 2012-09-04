package test.tilu.stuff.businessdelegate;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tilu.stuff.beans.CommentFormBeanImpl;
import org.tilu.stuff.businessdelegate.WebBussinessDelegate;
import org.tilu.stuff.businessdelegate.WebBussinessDelegateImpl;
import org.tilu.stuff.exceptions.CommunicationStuffException;
import org.tilu.stuff.tools.StatusEnum;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/runVisitorComments-ctx.xml" })

public class WebBusinessTest {
	private final static Logger logger = LoggerFactory.getLogger(WebBusinessTest.class);
	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMockito() {
		
			//arrange
				Iterator i=mock(Iterator.class);
				when(i.next()).thenReturn("Hello").thenReturn("World");
				//act
				String result=i.next()+" "+i.next();
				//assert
				assertEquals("Hello World", result);
	}
	@Test(expected= IllegalArgumentException.class)
	public void createBlankCommentPolicy(){
		CommentFormBeanImpl mCommentFormBean =mock(CommentFormBeanImpl.class);
		when (mCommentFormBean.getCommentText() ).thenReturn("");
		//assertEquals ("Esto es un mock  test.", mCommentFormBean.getCommentText());
		WebBussinessDelegate webBussiness= new WebBussinessDelegateImpl();
		webBussiness.createCommentPolicy(mCommentFormBean) ;
	}
	@Test(expected= IllegalArgumentException.class)
	public void createErrorCommentPolicy(){
		CommentFormBeanImpl mCommentFormBean =mock(CommentFormBeanImpl.class);
		when (mCommentFormBean.getCommentText() ).thenReturn(null);
		//assertEquals ("Esto es un mock  test.", mCommentFormBean.getCommentText());
		WebBussinessDelegate webBussiness= new WebBussinessDelegateImpl();
		webBussiness.createCommentPolicy(mCommentFormBean) ;
	}
	@Test
	public void createSentCommentToQueue(){
		CommentFormBeanImpl mCommentFormBean =mock(CommentFormBeanImpl.class);
		when (mCommentFormBean.getCommentText() ).thenReturn("This is a succesful message");
		//assertEquals ("Esto es un mock  test.", mCommentFormBean.getCommentText());
		WebBussinessDelegate webBussiness= new WebBussinessDelegateImpl();
		
		assertEquals (StatusEnum.SUCCESS, webBussiness.createCommentPolicy(mCommentFormBean) );
	}
}
