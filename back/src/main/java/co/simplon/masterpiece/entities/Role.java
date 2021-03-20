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
@Table(name = "roles", uniqueConstraints = {
		@UniqueConstraint(name = "role_code_UQ", columnNames = { "code" }) })
public class Role extends AbstractId {

	@Column(length = 45, nullable = false)
	private String code;

	@Column(nullable = false)
	private boolean defaultRole;

}
