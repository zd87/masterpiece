package co.simplon.masterpiece.services;

import co.simplon.masterpiece.dtos.UserDto;
import co.simplon.masterpiece.dtos.UserViewDto;

public interface IUserService {
//DELETE IF TESTS WELL
//	boolean checkUnicity(String sesameId);

	void create(UserDto newUserDto);

	UserViewDto getCurrentUserInfo(Long id);
}
