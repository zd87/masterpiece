package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.masterpiece.entities.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

//	@Query("select distinct(a.name) from Attribute a") //DELETE CANNOT MAKE WORK
	@Query(value = "select distinct attr_name from attributes", nativeQuery = true)
	public List<String> getNames();

	@Query("select a.attrValue from Attribute a where a.attrName=:attrName ")
	public List<String> getValues(@Param("attrName") String attrName);

	public Attribute findByAttrNameAndAttrValue(String name, String value);
}
