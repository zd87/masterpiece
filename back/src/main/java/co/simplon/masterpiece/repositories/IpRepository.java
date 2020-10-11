package co.simplon.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.Ip;

public interface IpRepository extends JpaRepository<Ip, Long> {

	Ip findByIp(String ip);

}
