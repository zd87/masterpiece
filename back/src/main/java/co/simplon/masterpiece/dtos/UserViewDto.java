package co.simplon.masterpiece.dtos;

import java.util.Set;

import co.simplon.masterpiece.entities.Role;

public interface UserViewDto {

	Long getId();

	String getLogin();

	Set<Role> getRoles();
}
