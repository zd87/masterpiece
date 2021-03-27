package co.simplon.masterpiece.services;

import java.util.List;

import co.simplon.masterpiece.entities.AuditEntry;

public interface IAuditEntryService {

	List<AuditEntry> getAll();
}
