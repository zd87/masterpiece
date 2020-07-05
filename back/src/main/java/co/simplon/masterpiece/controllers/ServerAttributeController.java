package co.simplon.masterpiece.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.masterpiece.services.ServerAttributeService;

@RestController
@CrossOrigin("*")
public class ServerAttributeController {

	private ServerAttributeService service;

	public ServerAttributeController(ServerAttributeService service) {
		this.service = service;
	}

	@GetMapping("/serverAttributeNames")
	protected List<String> server() {
		return service.getNames();
	}

}
