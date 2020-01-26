package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User extends AbstractId{
	@Column(length = 7, nullable = false, unique = true)
	private String sesameId;
	@Column(length = 255, nullable = false)
	private String pwd;
	
	public User() {
	}

	public User(String sesameId, String pwd) {
		this.sesameId = sesameId;
		this.pwd = pwd;
	}

	public String getSesameId() {
		return sesameId;
	}

	public void setSesameId(String sesameId) {
		this.sesameId = sesameId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	} 
	
}
