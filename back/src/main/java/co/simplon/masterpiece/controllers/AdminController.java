package co.simplon.masterpiece.controllers;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.dtos.RoleMgmtUserViewDto;
import co.simplon.masterpiece.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

	private final UserService userService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/users")
	protected List<RoleMgmtUserViewDto> getAll() {
		return userService.getAllRoleMgmtUser();
	}
}
