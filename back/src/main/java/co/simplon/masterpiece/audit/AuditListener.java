package co.simplon.masterpiece.audit;

import javax.persistence.EntityManager;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.masterpiece.entities.AuditEntry;
import co.simplon.masterpiece.entities.Server;

public class AuditListener {

	@Autowired
	private ApplicationContext context;

	SecurityContextHolder securityContext;

	private ObjectMapper objectMapper;

	private AuditEntry newEntry;

	private String oldCopyJson;

	public AuditListener(ObjectMapper objectMapper) {
		this.objectMapper = new ObjectMapper();
	}

	@PostLoad
	public void postLoad(Server target) throws JsonProcessingException {
		oldCopyJson = objectMapper.writeValueAsString(target);
	}

	@PostPersist
	public void postPersist(Server target) throws JsonProcessingException {
		String createdServer = objectMapper.writeValueAsString(target);
		newEntry = new AuditEntry(null, createdServer, Action.INSERTED);
		perform(newEntry);
	}

	@PostUpdate
	public void postUpdate(Server target) throws JsonProcessingException {
		String updatedServer = objectMapper.writeValueAsString(target);
		newEntry = new AuditEntry(updatedServer, oldCopyJson, Action.UPDATED);
		perform(newEntry);
	}

	@PreRemove
	public void preRemove(Server target) throws JsonProcessingException {
		newEntry = new AuditEntry(oldCopyJson, null, Action.DELETED);
	}

	@PostRemove
	public void postRemove(Server target) {
		perform(newEntry);
	}

	private void perform(AuditEntry newEntry) {
		newEntry.setClientIp(getAuditorsIp());
		EntityManager entityManager = context.getBean(EntityManager.class);
		entityManager.persist(newEntry);
	}

	public String getAuditorsIp() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication
				.getDetails();
		return details.getRemoteAddress();
	}

}
