package co.simplon.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.config.SecurityHelper;
import co.simplon.masterpiece.dtos.UserCreateDto;
import co.simplon.masterpiece.dtos.UserViewDto;
import co.simplon.masterpiece.services.CustomUserDetailsService;
import co.simplon.masterpiece.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserController {

	private final UserService service;

	private final CustomUserDetailsService customUserDetailsService;

	/*
	 * already authenticated user SecurityHelper contains information about the authenticated
	 * user then authenticated user is retrieved from the database by id
	 */
	@GetMapping("/me")
	public UserViewDto userInfo() {
		Long userId = SecurityHelper.getUserId();
		return service.getCurrentUserInfo(userId);
	}

	@PostMapping("/create_account")
	protected void create(@Valid @RequestBody UserCreateDto newUserDto) {
		service.create(newUserDto);
	}
}
