package org.tilu.stuff.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class CommentFormBeanImpl implements CommentFormBean {
	private int beanid;
	private String commentText;
	private Boolean aprovedBy;
	@Override
	public int getBeanid() {
		return beanid;
	}
	public void setBeanid(int beanid) {
		this.beanid = beanid;
	}
	@Override
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	@Override
	public Boolean getAprovedBy() {
		return aprovedBy;
	}
	public void setAprovedBy(Boolean aprovedBy) {
		this.aprovedBy = aprovedBy;
	}
	
}
