package co.simplon.masterpiece.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.masterpiece.entities.Ip;

public interface IpRepository extends JpaRepository<Ip, Long> {

	Ip findByIp(String ip);

	@Query("select i.ip from Ip i")
	public List<String> getIps();

}
