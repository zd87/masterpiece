package co.simplon.masterpiece.dtos;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import co.simplon.masterpiece.entities.Server;

public class ServerDto {

	@NotBlank
	private String name;

	@NotBlank
	private String fullName;

	@NotBlank
	private String ip;

	@NotBlank
	private String country;

	@NotBlank
	private String perimeter;

	private List<@Valid ServerAttributeDto> attributes;

	public ServerDto() {

	}

	public ServerDto(Server server, List<ServerAttributeDto> attributes) {
		this.name = server.getName();
		this.fullName = server.getFullName();
		this.ip = server.getIp();
		this.country = server.getCountry();
		this.perimeter = server.getPerimeter();
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

	public List<ServerAttributeDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ServerAttributeDto> attributes) {
		this.attributes = attributes;
	}

	public String toString() {
		return "Name: '" + this.name + "', Full name: '" + this.fullName + "', ip: '"
				+ this.ip + "', Country: '" + this.country + "', perimeter: '"
				+ this.perimeter + "', attributes: " + attributes;
	}
}
