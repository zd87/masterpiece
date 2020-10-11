package co.simplon.masterpiece.dtos;

import javax.validation.constraints.NotBlank;

public class IpDto {

	@NotBlank
	private String ip;

	public IpDto(String ip) {
		this.ip = ip;
	}

	public IpDto() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
