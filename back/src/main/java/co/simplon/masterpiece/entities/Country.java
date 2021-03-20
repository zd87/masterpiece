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
@Table(name = "countries", uniqueConstraints = {
		@UniqueConstraint(name = "country_name_UQ", columnNames = { "name" }) })
public class Country extends AbstractId {

	@Column(length = 45, nullable = false)
	private String name;

}
