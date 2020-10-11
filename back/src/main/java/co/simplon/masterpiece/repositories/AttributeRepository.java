package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.masterpiece.entities.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

	@Query(value = "select distinct attr_name from attributes", nativeQuery = true)
	public List<String> getNames();

	@Query(value = "select distinct attr_value from attributes", nativeQuery = true)
	public List<String> getValues();

	public Attribute findByAttrNameAndAttrValue(String name, String value);
}
