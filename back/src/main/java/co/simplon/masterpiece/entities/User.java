package co.simplon.masterpiece.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractId {

	@Column(length = 255, nullable = false)
	private String firstname;

	@Column(length = 255, nullable = false)
	private String lastname;

	@Column(length = 7, nullable = false, unique = true)
	private String login;

	@Column(length = 255, nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(String firstname, String lastname, String login, String password,
			Set<Role> roles) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
