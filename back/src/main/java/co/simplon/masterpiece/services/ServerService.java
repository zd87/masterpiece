package co.simplon.masterpiece.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.ServerAttributeDto;
import co.simplon.masterpiece.dtos.ServerAttributeViewDto;
import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.dtos.ServerViewDto;
import co.simplon.masterpiece.entities.Country;
import co.simplon.masterpiece.entities.Ip;
import co.simplon.masterpiece.entities.Perimeter;
import co.simplon.masterpiece.entities.Server;
import co.simplon.masterpiece.entities.ServerAttribute;
import co.simplon.masterpiece.repositories.CountryRepository;
import co.simplon.masterpiece.repositories.IpRepository;
import co.simplon.masterpiece.repositories.PerimeterRepository;
import co.simplon.masterpiece.repositories.ServerAttributeRepository;
import co.simplon.masterpiece.repositories.ServerRepository;

@Service
public class ServerService implements IServerService {

	private final ServerRepository serverRepo;

	private final ServerAttributeRepository serverAttributeRepo;

	private final CountryRepository countryRepo;

	private final PerimeterRepository perimeterRepo;

	private final IpRepository ipRepo;

	public ServerService(ServerRepository serverRepo, ServerAttributeRepository serverAttributeRepo,
			CountryRepository countryRepo, PerimeterRepository perimeterRepo, IpRepository ipRepo) {
		this.serverRepo = serverRepo;
		this.serverAttributeRepo = serverAttributeRepo;
		this.countryRepo = countryRepo;
		this.perimeterRepo = perimeterRepo;
		this.ipRepo = ipRepo;
	}

	public List<ServerViewDto> getAll() {
		List<Server> serversFromRepo = serverRepo.findAll();
		List<ServerViewDto> servers = new ArrayList<>();
		for (Server server : serversFromRepo) {
			ServerViewDto convertedDto = convertServerToDto(server);
			servers.add(convertedDto);
		}
		return servers;
	}

	public void create(ServerDto serverDto) {
//		/* creating set of attributes */
//		Set<ServerAttribute> attributes = saveServerAttributes(serverDto.getAttributes());

//		/* composing a Server */
//		Server newServer = new Server(serverDto.getName(), serverDto.getFullName(),
//				serverDto.getIp(), serverDto.getCountry(), serverDto.getPerimeter(),
//				attributes);
		Server newServer = convertDtoToServer(serverDto);
		serverRepo.save(newServer);
	}

	public void update(Long id, ServerDto serverDto) {
//		/* creating set of attributes */
//		Set<ServerAttribute> attributes = saveServerAttributes(serverDto.getAttributes());

		/* updating server */
		Server serverToUpdate = serverRepo.getOne(id);
//		serverToUpdate.setServer(serverDto.getName(), serverDto.getFullName(),
//				serverDto.getIp(), serverDto.getCountry(), serverDto.getPerimeter(),
//				attributes);
		Server serverFromDto = convertDtoToServer(serverDto);
		serverToUpdate.setServer(serverFromDto);
		serverRepo.save(serverToUpdate);
	}

	public void deleteById(Long id) {
		serverRepo.deleteById(id);
	}

	public Server convertDtoToServer(ServerDto serverDto) {
		Set<ServerAttribute> attributes = saveNewServerAttributesAndReturnConverted(
				serverDto.getAttributes());
		Country country = createCountryIfNotExistAndReturnOne(serverDto.getCountry());
		Perimeter perimeter = createPerimeterIfNotExistAndReturnOne(serverDto.getPerimeter());
		Ip ip = createIpIfNotExistAndReturnOne(serverDto.getIp());
		Server newServer = new Server(serverDto.getName(), serverDto.getFullName(), ip, country,
				perimeter, attributes);
		return newServer;
	}

	public ServerViewDto convertServerToDto(Server server) {
		String country = server.getCountry().getName();
		String perimeter = server.getPerimeter().getName();
		String ip = server.getIp().getIp();
		List<ServerAttributeViewDto> attributes = convertAttributesToDto(server.getAttributes());
		ServerViewDto dto = new ServerViewDto(server.getId(), server.getName(),
				server.getFullName(), ip, country, perimeter, attributes);
		return dto;
	}

	public List<ServerAttributeViewDto> convertAttributesToDto(Set<ServerAttribute> attributes) {
		List<ServerAttributeViewDto> attributeDtos = new ArrayList<>();
		for (ServerAttribute attribute : attributes) {
			ServerAttributeViewDto attributeDto = new ServerAttributeViewDto(
					attribute.getAttrName(), attribute.getAttrValue());
			attributeDtos.add(attributeDto);
		}
		return attributeDtos;
	}

	public Set<ServerAttribute> saveNewServerAttributesAndReturnConverted(
			List<ServerAttributeDto> attributes) {
		Set<ServerAttribute> convertedAttributes = new HashSet<ServerAttribute>();
		for (ServerAttributeDto attr : attributes) {
			ServerAttribute savedAttribute = createAttributeIfNotExistAndReturnOne(
					attr.getAttrName(), attr.getAttrValue());
			convertedAttributes.add(savedAttribute);
		}
		return convertedAttributes;
	}

	public ServerAttribute createAttributeIfNotExistAndReturnOne(String name, String value) {
		ServerAttribute attributeFromRepo = serverAttributeRepo.findByAttrNameAndAttrValue(name,
				value);
		if (attributeFromRepo == null) {
			ServerAttribute newAttribute = new ServerAttribute(name, value);
			serverAttributeRepo.save(newAttribute);
			attributeFromRepo = newAttribute;
		}
		return attributeFromRepo;
	}

	public Country createCountryIfNotExistAndReturnOne(String name) {
		Country countryFromRepo = countryRepo.findByName(name);
		if (countryFromRepo == null) {
			Country newCountry = new Country(name);
			countryRepo.save(newCountry);
			countryFromRepo = newCountry;
		}
		return countryFromRepo;
	}

	public Perimeter createPerimeterIfNotExistAndReturnOne(String name) {
		Perimeter perimeterFromRepo = perimeterRepo.findByName(name);
		if (perimeterFromRepo == null) {
			Perimeter newPerimeter = new Perimeter(name);
			perimeterRepo.save(newPerimeter);
			perimeterFromRepo = newPerimeter;
		}
		return perimeterFromRepo;
	}

	public Ip createIpIfNotExistAndReturnOne(String ip) {
		Ip ipFromRepo = ipRepo.findByIp(ip);
		if (ipFromRepo == null) {
			Ip newIp = new Ip(ip);
			ipRepo.save(newIp);
			ipFromRepo = newIp;
		}
		return ipFromRepo;
	}

}
