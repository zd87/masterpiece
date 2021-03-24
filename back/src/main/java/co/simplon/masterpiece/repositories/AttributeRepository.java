package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.masterpiece.entities.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

	@Query(value = "select distinct name from attributes", nativeQuery = true)
	public List<String> getNames();

	@Query("select a.value from Attribute a where a.name=:attrName ")
	public List<String> getValues(@Param("attrName") String attrName);

	public Attribute findByNameAndValue(String name, String value);

	/* Get attribute names that are currently referenced */
	@Query(value = "select distinct a.name from attributes a inner join server_attribute sa on sa.attribute_id=a.id order by a.name asc", nativeQuery = true)
	public List<String> getCurrentlyUsedAttrNames();
}
