package co.simplon.masterpiece.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.entities.AuditEntry;
import co.simplon.masterpiece.repositories.AuditEntryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuditEntryService implements IAuditEntryService {

	private final AuditEntryRepository auditRepo;

	@Override
	public List<AuditEntry> getAll() {
		return auditRepo.findAll();
	}

}
