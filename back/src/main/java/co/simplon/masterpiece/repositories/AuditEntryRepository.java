package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.AuditEntry;

public interface AuditEntryRepository extends JpaRepository<AuditEntry, Long> {

	List<AuditEntry> findAllByOrderByActionDateDesc();

}
