package co.simplon.masterpiece.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.dtos.UserDto;
import co.simplon.masterpiece.services.UserService;

@RestController
@RequestMapping("/create_account")
@CrossOrigin("*")
public class UserController {

	private final UserService service;

	protected UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/check_unicity/{sesame_id}")
	protected boolean checkUnicity(@PathVariable("sesame_id") String sesameId) {
		return service.checkUnicity(sesameId);
	}

	@PostMapping
	protected void create(@Valid @RequestBody UserDto newUserDto) {
		service.create(newUserDto);
	}
}
