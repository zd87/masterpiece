package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.masterpiece.entities.ServerAttribute;

public interface ServerAttributeRepository extends JpaRepository<ServerAttribute, Long> {

	/*
	 * @Query(value = "select save_and_get(:name, :value)", nativeQuery = true) public
	 * ServerMeta create(@Param("name") String name, @Param("value") String value);
	 */

	@Query(value = "select * from server_attributes where attr_name=:name and attr_value=:value", nativeQuery = true)
	public ServerAttribute getOne(@Param("name") String name,
			@Param("value") String value);

	@Transactional /*
					 * Without @Transactional annotation, exception: Executing an
					 * update/delete query
					 */
	@Modifying /*
				 * Without @Modifying annotation, exception: No results returned by the
				 * query
				 */
	@Query(value = "call insert_if_not_exist(:name, :value)", nativeQuery = true)
	public void createIfNotExist(@Param("name") String name,
			@Param("value") String value);

	@Query(value = "select distinct attr_name from server_attributes", nativeQuery = true)
	public List<String> getNames();

	@Query(value = "select distinct attr_value from server_attributes", nativeQuery = true)
	public List<String> getValues();
}
