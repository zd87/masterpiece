package co.simplon.masterpiece.dtos;

import java.util.List;

public class ServerViewDto {

	private Long id;

	private String name;

	private String fullName;

	private String ip;

	private String country;

	private String perimeter;

	private List<ServerAttributeViewDto> attributes;

	public ServerViewDto(Long id, String name, String fullName, String ip, String country,
			String perimeter, List<ServerAttributeViewDto> attributes) {
		this.id = id;
		this.name = name;
		this.fullName = fullName;
		this.ip = ip;
		this.country = country;
		this.perimeter = perimeter;
		this.attributes = attributes;
	}

	public ServerViewDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ServerAttributeViewDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ServerAttributeViewDto> attributes) {
		this.attributes = attributes;
	}

}
