package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perimeters", uniqueConstraints = {
		@UniqueConstraint(name = "perimeter_name_UQ", columnNames = { "name" }) })
public class Perimeter extends AbstractId {

	@Column(length = 45, nullable = false)
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Perimeter) {
			Perimeter test = (Perimeter) obj;
			if (test.getName().equals(this.name)) {
				return true;
			}
		}
		return false;
	}

}
