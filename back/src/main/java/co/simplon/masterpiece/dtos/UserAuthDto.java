package co.simplon.masterpiece.dtos;

import java.util.Set;

import co.simplon.masterpiece.entities.Role;

public interface UserAuthDto {

	Long getId();

	String getLogin();

	String getPassword();

	Set<Role> getRoles();

//	boolean isEnabled();

//	boolean isAccountNonExpired();

//	boolean isAccountNonLocked();

//	boolean isCredentialsNonExpired();
}
