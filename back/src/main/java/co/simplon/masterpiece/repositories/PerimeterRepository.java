package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.masterpiece.entities.Perimeter;

public interface PerimeterRepository extends JpaRepository<Perimeter, Long> {

	Perimeter findByName(String name);

	@Query("select p.name from Perimeter p")
	public List<String> getNames();

}
