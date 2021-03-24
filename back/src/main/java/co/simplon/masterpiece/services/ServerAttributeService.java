package co.simplon.masterpiece.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.AttributeSelectOptionsViewDto;
import co.simplon.masterpiece.repositories.AttributeRepository;
import co.simplon.masterpiece.repositories.CountryRepository;
import co.simplon.masterpiece.repositories.IpRepository;
import co.simplon.masterpiece.repositories.PerimeterRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServerAttributeService implements IServerAttributeService {

	private AttributeRepository attributeRepo;

	private CountryRepository countryRepo;

	private PerimeterRepository perimeterRepo;

	private IpRepository ipRepo;

	@Override
	public AttributeSelectOptionsViewDto getAllValues() {
		HashMap<String, List<String>> attributes = new HashMap();
		List<String> attrNames = attributeRepo.getNames();
		for (String attrName : attrNames) {
			attributes.put(attrName, getAttrValues(attrName));
		}
		List<String> perimeters = perimeterRepo.getNames();
		List<String> countries = countryRepo.getNames();
		List<String> ips = ipRepo.getIps();
		return new AttributeSelectOptionsViewDto(attributes, perimeters, countries, ips);
	}

	@Override
	public List<String> getAttrValues(String attrName) {
		List<String> attrValues = attributeRepo.getValues(attrName);
		return attrValues;
	};

	@Override
	public List<String> getCurrentlyUsedAttrNames() {
		return attributeRepo.getCurrentlyUsedAttrNames();
	}
}
