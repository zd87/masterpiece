package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.masterpiece.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByName(String name);

	@Query("select c.name from Country c")
	public List<String> getNames();

}
