package org.tilu.stuff.beans;


import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

@Service

public class CommentFormBeanImpl implements CommentFormBean {
	@Autowired
    private Validator validator;
	@GeneratedValue
	private Integer beanid;
	@NotNull
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
    /**
     * Constructs a <code>String</code> with all attributes
     * in name = value format.
     *
     * @return a <code>String</code> representation 
     * of this object.
     */
    public String toString() {
       StringBuilder sb = new StringBuilder("[[CommsPlan]");
       sb.append("[aprovedBy = ").append(this.aprovedBy).append("]");
       sb.append("[commentText = ").append(this.commentText).append("]");
       sb.append("[beanid = ").append(this.beanid).append("]");
       sb.append("]");
       return sb.toString();
    }	
}
