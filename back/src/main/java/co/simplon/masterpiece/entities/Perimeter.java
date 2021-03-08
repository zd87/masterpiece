package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "perimeters", uniqueConstraints = {
		@UniqueConstraint(name = "perimeter_name_UQ", columnNames = { "name" }) })
public class Perimeter extends AbstractId {

	@Column(length = 45)
	private String name;

	public Perimeter(String name) {
		this.name = name;
	}

	public Perimeter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
