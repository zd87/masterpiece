package co.simplon.masterpiece.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.AttributesViewDto;
import co.simplon.masterpiece.repositories.AttributeRepository;
import co.simplon.masterpiece.repositories.CountryRepository;
import co.simplon.masterpiece.repositories.IpRepository;
import co.simplon.masterpiece.repositories.PerimeterRepository;

@Service
public class ServerAttributeService {

	private AttributeRepository attributeRepo;

	private CountryRepository countryRepo;

	private PerimeterRepository perimeterRepo;

	private IpRepository ipRepo;

	public ServerAttributeService(AttributeRepository attributeRepo, CountryRepository countryRepo,
			PerimeterRepository perimeterRepo, IpRepository ipRepo) {
		this.attributeRepo = attributeRepo;
		this.countryRepo = countryRepo;
		this.perimeterRepo = perimeterRepo;
		this.ipRepo = ipRepo;
	}

	public AttributesViewDto getAllValues() {
		List<String> attrNames = attributeRepo.getNames();
		List<String> perimeters = perimeterRepo.getNames();
		List<String> countries = countryRepo.getNames();
		List<String> ips = ipRepo.getIps();
		return new AttributesViewDto(attrNames, perimeters, countries, ips);
	}

	public List<String> getAttrValues(String attrName) {
		List<String> attrValues = attributeRepo.getValues(attrName);
		return attrValues;
	};

}
