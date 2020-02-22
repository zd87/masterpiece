package co.simplon.masterpiece.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.masterpiece.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByDefaultRoleTrue();
}
