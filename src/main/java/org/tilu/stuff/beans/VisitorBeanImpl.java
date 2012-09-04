package org.tilu.stuff.beans;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class VisitorBeanImpl implements VisitorBean {
	@Id
	@GeneratedValue
	private Integer id;
	private String username="anonymous";
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitTime ;

	
	
	@Override
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}



	
	public Date getVisitTime() {
		return visitTime;
	}




	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}




	@Override
	public String toString(){
       StringBuilder sb = new StringBuilder("[[VisitorBeanImpl]");
       sb.append("[id = ").append(this.id).append("]");
       sb.append("[username = ").append(this.username).append("]");
       sb.append("[visitTime = ").append(this.visitTime).append("]");
       sb.append("]");
       return sb.toString();
	}
}
