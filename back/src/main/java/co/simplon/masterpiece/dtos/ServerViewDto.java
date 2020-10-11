package co.simplon.masterpiece.dtos;

import java.util.List;

public class ServerViewDto {

	private Long id;

	private String name;

	private String fullName;

	private IpViewDto ip;

	private CountryViewDto country;

	private PerimeterViewDto perimeter;

	private List<AttributeViewDto> attributes;

	public ServerViewDto(Long id, String name, String fullName, IpViewDto ip,
			CountryViewDto country, PerimeterViewDto perimeter, List<AttributeViewDto> attributes) {
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

	public IpViewDto getIp() {
		return ip;
	}

	public void setIp(IpViewDto ip) {
		this.ip = ip;
	}

	public CountryViewDto getCountry() {
		return country;
	}

	public void setCountry(CountryViewDto country) {
		this.country = country;
	}

	public PerimeterViewDto getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(PerimeterViewDto perimeter) {
		this.perimeter = perimeter;
	}

	public List<AttributeViewDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeViewDto> attributes) {
		this.attributes = attributes;
	}

}
