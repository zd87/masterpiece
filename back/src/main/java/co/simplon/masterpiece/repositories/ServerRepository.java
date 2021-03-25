package co.simplon.masterpiece.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.dtos.ServerViewDto;
import co.simplon.masterpiece.entities.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {

	Optional<Server> findById(Long id);

	List<ServerViewDto> findAllProjectedBy();

	boolean existsByFullName(String fullName);
	Server findByFullName(String fullName);
}
