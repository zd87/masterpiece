package co.simplon.masterpiece.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import co.simplon.masterpiece.audit.AuditListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "servers", uniqueConstraints = {
		@UniqueConstraint(name = "server_full_name_UQ", columnNames = { "fullName" }) })
public class Server extends AbstractId {

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "id_ip", nullable = false, foreignKey = @ForeignKey(name = "fk_servers_ips"))
	private Ip ip;

	@ManyToOne
	@JoinColumn(name = "id_country", nullable = false, foreignKey = @ForeignKey(name = "fk_servers_countries"))
	private Country country;

	@ManyToOne
	@JoinColumn(name = "id_perimeter", nullable = false, foreignKey = @ForeignKey(name = "fk_servers_perimeters"))
	private Perimeter perimeter;

	@ManyToMany
	@JoinTable(name = "server_attribute", joinColumns = @JoinColumn(name = "server_id", foreignKey = @ForeignKey(name = "fk_server_attribute_server")), inverseJoinColumns = @JoinColumn(name = "attribute_id", foreignKey = @ForeignKey(name = "fk_server_attribute_attribute")))
	private Set<Attribute> attributes;

	public void setServer(Server newServer) {
		this.name = newServer.getName();
		this.fullName = newServer.getFullName();
		this.ip = newServer.getIp();
		this.country = newServer.getCountry();
		this.perimeter = newServer.getPerimeter();
		this.attributes = newServer.getAttributes();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Server) {
			Server test = (Server) obj;
			boolean sameName = test.getName().equals(this.name)
					&& test.getFullName().equals(this.fullName);
			boolean sameNestedObj = test.getIp().equals(this.ip)
					&& test.getCountry().equals(this.country)
					&& test.getPerimeter().equals(this.perimeter);
			boolean sameAttributes = test.getAttributes().size() == this.attributes.size()
					&& test.getAttributes().containsAll(this.attributes);
			if (sameName && sameNestedObj && sameAttributes) {
				return true;
			}
		}
		return false;
	}

}
