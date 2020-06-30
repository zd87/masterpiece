package co.simplon.masterpiece.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {

	Optional<Server> findById(Long id);
}
