package org.tilu.stuff.businessdelegate;

import org.tilu.stuff.beans.CommentFormBean;
import org.tilu.stuff.tools.StatusEnum;

public interface WebBussinessDelegate {
	public StatusEnum createCommentPolicy(CommentFormBean mCommentFormBean);
	public StatusEnum deleteCommentPolicy(CommentFormBean mCommentFormBean);
}
