package org.tilu.stuff.businessdelegate;

import org.tilu.stuff.beans.CommentFormBean;

public interface WebBussinessDelegate {
	public String createCommentPolicy(CommentFormBean mCommentFormBean);
	public void deleteCommentPolicy(CommentFormBean mCommentFormBean);
}
