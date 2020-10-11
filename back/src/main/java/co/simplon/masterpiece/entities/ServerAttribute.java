package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "server_attributes", uniqueConstraints = {
		@UniqueConstraint(name = "attribute_name_value_UQ", columnNames = { "attrName",
				"attrValue" }) })
public class ServerAttribute extends AbstractId {

	@Column(length = 45, nullable = false)
	private String attrName;

	@Column(length = 255, nullable = false)
	private String attrValue;

	public ServerAttribute() {
	}

	public ServerAttribute(String attrName, String attrValue) {
		this.attrName = attrName;
		this.attrValue = attrValue;
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
