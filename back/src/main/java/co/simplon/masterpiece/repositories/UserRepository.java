package co.simplon.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.masterpiece.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findBySesameId(String sesameId);
}
