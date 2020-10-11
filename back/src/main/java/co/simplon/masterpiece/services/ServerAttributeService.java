package co.simplon.masterpiece.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.AttributesViewDto;
import co.simplon.masterpiece.repositories.AttributeRepository;

@Service
public class ServerAttributeService {

	private AttributeRepository serverAttributes;

	public ServerAttributeService(AttributeRepository serverAttributes) {
		this.serverAttributes = serverAttributes;
	}

	public AttributesViewDto getValues() {
		List<String> names = serverAttributes.getNames();
		List<String> values = serverAttributes.getValues();
		return new AttributesViewDto(names, values);
	};

}
