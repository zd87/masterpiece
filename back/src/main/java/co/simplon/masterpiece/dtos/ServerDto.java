package co.simplon.masterpiece.dtos;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class ServerDto {

	@NotBlank
	private String name;

	@NotBlank
	private String fullName;

	@Valid
	private IpDto ip;

	@Valid
	private CountryDto country;

	@Valid
	private PerimeterDto perimeter;

	private List<@Valid AttributeDto> attributes;

	public ServerDto() {

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

	public IpDto getIp() {
		return ip;
	}

	public void setIp(IpDto ip) {
		this.ip = ip;
	}

	public CountryDto getCountry() {
		return country;
	}

	public void setCountry(CountryDto country) {
		this.country = country;
	}

	public PerimeterDto getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(PerimeterDto perimeter) {
		this.perimeter = perimeter;
	}

	public List<AttributeDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeDto> attributes) {
		this.attributes = attributes;
	}

	public String toString() {
		return "Name: '" + this.name + "', Full name: '" + this.fullName + "', ip: '" + this.ip
				+ "', Country: '" + this.country + "', perimeter: '" + this.perimeter
				+ "', attributes: " + attributes;
	}
}
