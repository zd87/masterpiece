package co.simplon.masterpiece.dtos;

public class AttributeViewDto {

	private String attrName;

	private String attrValue;

	public AttributeViewDto(String attrName, String attrValue) {
		this.attrName = attrName;
		this.attrValue = attrValue;
	}

	public AttributeViewDto() {
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
