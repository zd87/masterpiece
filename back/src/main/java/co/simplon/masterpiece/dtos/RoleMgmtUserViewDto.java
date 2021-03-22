package co.simplon.masterpiece.dtos;

import java.util.Set;

public interface RoleMgmtUserViewDto {

	Long getId();

	String getFirstname();

	String getLastname();

	Set<RoleViewDto> getRoles();
}
