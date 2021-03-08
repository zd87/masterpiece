package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ips", uniqueConstraints = {
		@UniqueConstraint(name = "ip_ip_UQ", columnNames = { "ip" }) })
public class Ip extends AbstractId {

	@Column(length = 45)
	private String ip;

	public Ip(String ip) {
		this.ip = ip;
	}

	public Ip() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
