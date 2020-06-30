package co.simplon.masterpiece.dtos;

import java.util.List;

import co.simplon.masterpiece.entities.Server;

public class ServerDto {

	private String name;

	private String fullName;

	private String ip;

	private String country;

	private String perimeter;

	private List<ServerAttributeDto> attributes;

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

}
