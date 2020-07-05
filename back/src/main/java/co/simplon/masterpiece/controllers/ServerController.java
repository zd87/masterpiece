package co.simplon.masterpiece.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.entities.Server;
import co.simplon.masterpiece.services.ServerService;

@RestController
@RequestMapping("/servers")
@CrossOrigin("*")
public class ServerController {

	private final ServerService service;

	protected ServerController(ServerService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	protected ServerDto server(@PathVariable("id") Long id) {
		return service.getOne(id);
	}

	@GetMapping
	protected List<Server> getAll() {
		return service.getAll();
	}

	@PostMapping
	protected void post(@Valid @RequestBody ServerDto serverDto) {
		service.create(serverDto);
	}

	@PostMapping("/{id}")
	protected void update(@PathVariable("id") Long id,
			@Valid @RequestBody ServerDto serverDto) {
		System.out.println("from controller" + serverDto);
		service.update(id, serverDto);
	}

	/*
	 * @PostMapping protected void create(@Valid @RequestBody UserDto newUserDto) {
	 * service.create(newUserDto); }
	 */
}
