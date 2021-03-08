package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "countries", uniqueConstraints = {
		@UniqueConstraint(name = "country_name_UQ", columnNames = { "name" }) })
public class Country extends AbstractId {

	@Column(length = 45)
	private String name;

	public Country(String name) {
		this.name = name;
	}

	public Country() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
