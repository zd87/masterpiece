package co.simplon.masterpiece.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servers")
public class Server extends AbstractId {

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "id_ip", nullable = false, foreignKey = @ForeignKey(name = "fk_servers_ips"))
	private Ip ip;

	@ManyToOne
	@JoinColumn(name = "id_country", nullable = false, foreignKey = @ForeignKey(name = "fk_servers_countries"))
	private Country country;

	@ManyToOne
	@JoinColumn(name = "id_perimeter", nullable = false, foreignKey = @ForeignKey(name = "fk_servers_perimeters"))
	private Perimeter perimeter;

	@ManyToMany
	@JoinTable(name = "server_attribute", joinColumns = @JoinColumn(name = "server_id"), inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	private Set<Attribute> attributes;

	public Server() {
	}

	public Server(String name, String fullName, Ip ip, Country country,
			Perimeter perimeter, Set<Attribute> attributes) {
		this.name = name;
		this.fullName = fullName;
		this.ip = ip;
		this.country = country;
		this.perimeter = perimeter;
		this.attributes = attributes;
	}

	public void setServer(Server newServer) {
		this.name = newServer.getName();
		this.fullName = newServer.getFullName();
		this.ip = newServer.getIp();
		this.country = newServer.getCountry();
		this.perimeter = newServer.getPerimeter();
		this.attributes = newServer.getAttributes();
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

	public Ip getIp() {
		return ip;
	}

	public void setIp(Ip ip) {
		this.ip = ip;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Perimeter getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(Perimeter perimeter) {
		this.perimeter = perimeter;
	}

	public Set<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String toString() {
		return "Name: '" + this.name + "', Full name: '" + this.fullName + "', ip: '"
				+ this.ip + "', Country: '" + this.country + "', perimeter: '"
				+ this.perimeter + "', attributes: " + attributes;
	}

}
