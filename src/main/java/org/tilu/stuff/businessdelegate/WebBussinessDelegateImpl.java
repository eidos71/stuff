package org.tilu.stuff.businessdelegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.exceptions.CommunicationStuffException;
import org.tilu.stuff.tools.StatusEnum;


public class WebBussinessDelegateImpl implements WebBussinessDelegate{
	private final static Logger logger = LoggerFactory.getLogger(WebBussinessDelegateImpl.class);
	@Override
	public StatusEnum createCommentPolicy(CommentFormBean mCommentFormBean)  throws CommunicationStuffException {
		if (mCommentFormBean==null ||  mCommentFormBean.getCommentText()==null || mCommentFormBean.getCommentText().isEmpty())
			throw new IllegalArgumentException ("Invalid argument coming from CommentFormBean, null or empty body");
		//if we are here we could send the element to the message queue...
		try{
			//we need to check if the element is succesful once the message it is done just its a forget.
			if (logger.isDebugEnabled()) logger.debug("We sent the message and forget about it{} ", mCommentFormBean.getCommentText());
		}catch (Exception err ){
			logger.error("CommentMesageBeanError, cause {}", err.getMessage());
			throw new CommunicationStuffException(err.getCause());
		}
		return StatusEnum.SUCCESS;
		
	}
	
	@Override
	public StatusEnum deleteCommentPolicy(CommentFormBean mCommentFormBean) {
		// TODO Auto-generated method stub
		return null;
	}

}
