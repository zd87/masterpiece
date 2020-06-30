package co.simplon.masterpiece.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servers")
public class Server extends AbstractId {

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String fullName;

	@Column(length = 45)
	private String ip;

	@Column(length = 45)
	private String country;

	@Column(length = 45, name = "perimeter")
	private String perimeter;

	@ManyToMany
	@JoinTable(name = "server_attribute", joinColumns = @JoinColumn(name = "server_id"), inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	private Set<ServerAttribute> attributes;

	public Server() {
	}

	public Server(String name, String fullName, String ip, String country,
			String perimeter, Set<ServerAttribute> attributes) {
		this.name = name;
		this.fullName = fullName;
		this.ip = ip;
		this.country = country;
		this.perimeter = perimeter;
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(String perimeter) {
		this.perimeter = perimeter;
	}

	public Set<ServerAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<ServerAttribute> attributes) {
		this.attributes = attributes;
	}

}
