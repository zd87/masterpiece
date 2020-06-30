package co.simplon.masterpiece.dtos;

public class ServerAttributeDto {

	private String attrName;

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
