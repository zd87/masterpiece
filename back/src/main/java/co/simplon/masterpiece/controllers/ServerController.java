package co.simplon.masterpiece.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.dtos.AttributeSelectOptionsViewDto;
import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.dtos.ServerViewDto;
import co.simplon.masterpiece.services.ServerAttributeService;
import co.simplon.masterpiece.services.ServerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/servers")
public class ServerController {

	private final ServerService serverService;

	private final ServerAttributeService attrService;

	@GetMapping
	protected List<ServerViewDto> getAll() {
		return serverService.getAll();
	}

	@Secured("ROLE_ADMIN")
	@PostMapping
	protected void post(@Valid @RequestBody ServerDto serverDto) {
		serverService.create(serverDto);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	protected void update(@PathVariable("id") Long id, @Valid @RequestBody ServerDto serverDto) {
		serverService.update(id, serverDto);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		serverService.deleteById(id);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/attributes")
	protected AttributeSelectOptionsViewDto attributes() {
		return attrService.getAllValues();
	}

}
