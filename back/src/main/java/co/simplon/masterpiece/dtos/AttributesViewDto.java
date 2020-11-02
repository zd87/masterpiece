package co.simplon.masterpiece.dtos;

import java.util.List;

public class AttributesViewDto {

	private List<String> attrNames;

	private List<String> perimeters;

	private List<String> countries;

	private List<String> ips;

	public AttributesViewDto() {
	}

	public AttributesViewDto(List<String> attrNames, List<String> perimeters,
			List<String> countries, List<String> ips) {
		this.attrNames = attrNames;
		this.perimeters = perimeters;
		this.countries = countries;
		this.ips = ips;
	}

	public List<String> getAttrNames() {
		return attrNames;
	}

	public void setAttrNames(List<String> attrNames) {
		this.attrNames = attrNames;
	}

	public List<String> getPerimeters() {
		return perimeters;
	}

	public void setPerimeters(List<String> perimeters) {
		this.perimeters = perimeters;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getIps() {
		return ips;
	}

	public void setIps(List<String> ips) {
		this.ips = ips;
	}

}
