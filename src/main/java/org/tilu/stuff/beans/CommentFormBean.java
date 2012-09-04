package org.tilu.stuff.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


public interface CommentFormBean {

	Boolean getAprovedBy();

	String getCommentText();

	int getBeanid();

}
