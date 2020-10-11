package co.simplon.masterpiece.dtos;

public class ServerAttributeViewDto {

	private String attrName;

	private String attrValue;

	public ServerAttributeViewDto(String attrName, String attrValue) {
		this.attrName = attrName;
		this.attrValue = attrValue;
	}

	public ServerAttributeViewDto() {
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

}
