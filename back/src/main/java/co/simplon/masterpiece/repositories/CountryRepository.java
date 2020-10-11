package co.simplon.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByName(String name);

}
