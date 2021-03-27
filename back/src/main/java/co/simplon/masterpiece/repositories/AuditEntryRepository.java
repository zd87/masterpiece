package co.simplon.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.AuditEntry;

public interface AuditEntryRepository extends JpaRepository<AuditEntry, Long> {

}
