package co.simplon.masterpiece.dtos;

import javax.validation.constraints.NotBlank;

public class ServerAttributeDto {

	@NotBlank
	private String attrName;

	@NotBlank
	private String attrValue;

	public ServerAttributeDto() {
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	@Override
	public String toString() {
		return "attrName: " + this.attrName + ", attrValue: " + this.attrValue;
	}
}
