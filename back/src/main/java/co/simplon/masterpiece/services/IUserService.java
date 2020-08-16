package co.simplon.masterpiece.services;

import co.simplon.masterpiece.dtos.UserCreateDto;
import co.simplon.masterpiece.dtos.UserViewDto;

public interface IUserService {
//DELETE IF TESTS WELL
//	boolean checkUnicity(String sesameId);

	void create(UserCreateDto newUserDto);

	UserViewDto getCurrentUserInfo(Long id);
}
