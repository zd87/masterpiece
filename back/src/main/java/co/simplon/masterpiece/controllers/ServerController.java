package co.simplon.masterpiece.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.dtos.ServerAttributesViewDto;
import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.entities.Server;
import co.simplon.masterpiece.services.ServerAttributeService;
import co.simplon.masterpiece.services.ServerService;

@RestController
@RequestMapping("/servers")
public class ServerController {

	private final ServerService serverService;

	private final ServerAttributeService attrService;

	public ServerController(ServerService serverService,
			ServerAttributeService attrService) {
		this.serverService = serverService;
		this.attrService = attrService;
	}

	@GetMapping("/{id}")
	protected ServerDto server(@PathVariable("id") Long id) {
		return serverService.getOne(id);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		serverService.deleteById(id);
	}

	@GetMapping
	protected List<Server> getAll() {
		return serverService.getAll();
	}

	@PostMapping
	protected void post(@Valid @RequestBody ServerDto serverDto) {
		serverService.create(serverDto);
	}

	@PostMapping("/{id}")
	protected void update(@PathVariable("id") Long id,
			@Valid @RequestBody ServerDto serverDto) {
		serverService.update(id, serverDto);
	}

	@GetMapping("/attributes")
	protected ServerAttributesViewDto attributes() {
		return attrService.getValues();
	}
}
