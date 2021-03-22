package co.simplon.masterpiece.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.masterpiece.dtos.RoleMgmtUserViewDto;
import co.simplon.masterpiece.dtos.UserAuthDto;
import co.simplon.masterpiece.dtos.UserViewDto;
import co.simplon.masterpiece.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//DELETE IF TESTS WELL
//	User getByLogin(String login);

	Optional<UserAuthDto> findByLogin(String login);

//	Optional<UserAuthDto> findByUsername(String username);

	boolean existsByLogin(String value);

	Optional<UserViewDto> getById(Long id);

	List<RoleMgmtUserViewDto> findAllProjectedBy();

}
