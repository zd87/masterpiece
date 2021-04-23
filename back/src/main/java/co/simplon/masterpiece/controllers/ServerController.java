package co.simplon.masterpiece.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.masterpiece.dtos.AttributeSelectOptionsViewDto;
import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.dtos.ServerViewDto;
import co.simplon.masterpiece.services.ServerAttributeService;
import co.simplon.masterpiece.services.ServerService;
import co.simplon.masterpiece.utils.ReadServersFromExcel;
import co.simplon.masterpiece.utils.WriteServersToExcel;
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

	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		/* Response headers */
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
		String currentDateTime = dateFormatter.format(new Date());
		response.setHeader("Content-Disposition",
				"attachment; filename=servers_" + currentDateTime + ".xlsx");
		/* Get data for writing */
		List<ServerViewDto> servers = serverService.getAll();
		List<String> attrNames = attrService.getCurrentlyUsedAttrNames();

		/* Write Excel */
		WriteServersToExcel writeServersToExcel = new WriteServersToExcel(servers, attrNames);
		writeServersToExcel.export(response);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/excel")
	public void importFromExcel(@RequestParam("import-file") MultipartFile file)
			throws IOException {
		String filename = file.getOriginalFilename();
		ReadServersFromExcel readServersFromExcel = new ReadServersFromExcel();
		List<ServerDto> importedServers = readServersFromExcel.export(file);
		serverService.saveAll(importedServers);
	}

}
