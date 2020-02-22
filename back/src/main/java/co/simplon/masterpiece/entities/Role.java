package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends AbstractId {

	@Column(length = 45, nullable = false, unique = true)
	private String code;

	@Column(nullable = false)
	private boolean defaultRole;

	public Role() {
	}

	public Role(String code, boolean defaultRole) {
		this.code = code;
		this.defaultRole = defaultRole;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

}
