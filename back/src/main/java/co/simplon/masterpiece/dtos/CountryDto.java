package co.simplon.masterpiece.dtos;

import javax.validation.constraints.NotBlank;

public class CountryDto {

	@NotBlank
	private String name;

	public CountryDto(String name) {
		this.name = name;
	}

	public CountryDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
