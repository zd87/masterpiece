package co.simplon.masterpiece.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ips")
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
