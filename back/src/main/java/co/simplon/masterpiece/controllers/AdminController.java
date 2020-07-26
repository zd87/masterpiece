package co.simplon.masterpiece.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@PreAuthorize("hasRole('ADMIN')") // == @Secured("ROLE_ADMIN")
	@GetMapping
	public String admin() {
		return "Hello admin!";
	}
}
