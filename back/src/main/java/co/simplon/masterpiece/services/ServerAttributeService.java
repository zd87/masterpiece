package co.simplon.masterpiece.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.ServerAttributesViewDto;
import co.simplon.masterpiece.repositories.ServerAttributeRepository;

@Service
public class ServerAttributeService {

	private ServerAttributeRepository serverAttributes;

	public ServerAttributeService(ServerAttributeRepository serverAttributes) {
		this.serverAttributes = serverAttributes;
	}

	public ServerAttributesViewDto getValues() {
		List<String> names = serverAttributes.getNames();
		List<String> values = serverAttributes.getValues();
		return new ServerAttributesViewDto(names, values);
	};

}
