package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "perimeters")
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
