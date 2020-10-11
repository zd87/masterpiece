package co.simplon.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.Perimeter;

public interface PerimeterRepository extends JpaRepository<Perimeter, Long> {

	Perimeter findByName(String name);

}
