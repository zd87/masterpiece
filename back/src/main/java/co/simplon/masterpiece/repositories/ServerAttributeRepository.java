package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.masterpiece.entities.ServerAttribute;

public interface ServerAttributeRepository extends JpaRepository<ServerAttribute, Long> {

	@Query(value = "select distinct attr_name from server_attributes", nativeQuery = true)
	public List<String> getNames();

	@Query(value = "select distinct attr_value from server_attributes", nativeQuery = true)
	public List<String> getValues();

	public ServerAttribute findByAttrNameAndAttrValue(String name, String value);
}
