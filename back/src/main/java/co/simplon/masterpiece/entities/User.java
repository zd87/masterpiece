package co.simplon.masterpiece.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(name = "user_login_UQ", columnNames = { "login" }) })
public class User extends AbstractId {

	@Column(length = 45, nullable = false)
	private String firstname;

	@Column(length = 255, nullable = false)
	private String lastname;

	@Column(length = 7, nullable = false)
	private String login;

	@Column(length = 70, nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_role_user")), inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_role_role")))
	private Set<Role> roles;

}
