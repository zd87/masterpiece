package co.simplon.masterpiece.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.simplon.masterpiece.audit.Action;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "audit_entries")
@EntityListeners(AuditingEntityListener.class)
public class AuditEntry extends AbstractId {

	@CreatedBy
	protected String performedBy;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date actionDate;

	@Column(length = 1500)
	private String serverBefore;

	@Column(length = 1500)
	private String serverAfter;

	@Column(length = 45)
	@Enumerated(EnumType.STRING)
	private Action action;

	@Column(length = 20)
	private String clientIp;

	public AuditEntry(String serverBefore, String serverAfter, Action action) {
		this.serverBefore = serverBefore;
		this.serverAfter = serverAfter;
		this.action = action;
	}

}
