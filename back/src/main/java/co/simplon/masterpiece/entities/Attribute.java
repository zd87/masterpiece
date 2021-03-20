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
@Table(name = "attributes", uniqueConstraints = {
		@UniqueConstraint(name = "attribute_name_value_UQ", columnNames = { "name", "value" }) })
public class Attribute extends AbstractId {

	@Column(length = 45, nullable = false)
	private String name;

	@Column(length = 255, nullable = false)
	private String value;

}
