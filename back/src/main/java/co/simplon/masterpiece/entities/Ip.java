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
@Table(name = "ips", uniqueConstraints = {
		@UniqueConstraint(name = "ip_ip_UQ", columnNames = { "ip" }) })
public class Ip extends AbstractId {

	@Column(length = 45, nullable = false)
	private String ip;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Ip) {
			Ip test = (Ip) obj;
			if (test.getIp().equals(this.ip)) {
				return true;
			}
		}
		return false;
	}
}
