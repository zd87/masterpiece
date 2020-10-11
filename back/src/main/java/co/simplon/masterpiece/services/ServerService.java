package co.simplon.masterpiece.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.dtos.ServerViewDto;
import co.simplon.masterpiece.entities.Attribute;
import co.simplon.masterpiece.entities.Country;
import co.simplon.masterpiece.entities.Ip;
import co.simplon.masterpiece.entities.Perimeter;
import co.simplon.masterpiece.entities.Server;
import co.simplon.masterpiece.repositories.AttributeRepository;
import co.simplon.masterpiece.repositories.CountryRepository;
import co.simplon.masterpiece.repositories.IpRepository;
import co.simplon.masterpiece.repositories.PerimeterRepository;
import co.simplon.masterpiece.repositories.ServerRepository;

@Service
public class ServerService implements IServerService {

	private final ServerRepository serverRepo;

	private final AttributeRepository serverAttributeRepo;

	private final CountryRepository countryRepo;

	private final PerimeterRepository perimeterRepo;

	private final IpRepository ipRepo;

	private final ModelMapper mapper;

	public ServerService(ServerRepository serverRepo, AttributeRepository serverAttributeRepo,
			CountryRepository countryRepo, PerimeterRepository perimeterRepo, IpRepository ipRepo,
			ModelMapper mapper) {
		this.serverRepo = serverRepo;
		this.serverAttributeRepo = serverAttributeRepo;
		this.countryRepo = countryRepo;
		this.perimeterRepo = perimeterRepo;
		this.ipRepo = ipRepo;
		this.mapper = mapper;
	}

	public List<ServerViewDto> getAll() {
		List<Server> serversFromRepo = serverRepo.findAll();
		List<ServerViewDto> servers = new ArrayList<>();
		serversFromRepo.forEach(server -> {
			ServerViewDto convertedDto = mapper.map(server, ServerViewDto.class);
			servers.add(convertedDto);
		});
		return servers;
	}

	public void create(ServerDto serverDto) {
		Server newServer = convertDtoToServer(serverDto);
		serverRepo.save(newServer);
	}

	public void update(Long id, ServerDto serverDto) {
		Server serverToUpdate = serverRepo.getOne(id);
		Server server = convertDtoToServer(serverDto);
		serverToUpdate.setServer(server);
		serverRepo.save(serverToUpdate);
	}

	public void deleteById(Long id) {
		serverRepo.deleteById(id);
	}

	private Server convertDtoToServer(ServerDto serverDto) {
		Server newServer = mapper.map(serverDto, Server.class);
		newServer.setIp(createIpIfNotExist(newServer));
		newServer.setCountry(createCountryIfNotExist(newServer));
		newServer.setPerimeter(createPerimeterIfNotExist(newServer));
		newServer.setAttributes(getAttributesFromRepo(newServer.getAttributes()));
		return newServer;
	}

	private Set<Attribute> getAttributesFromRepo(Set<Attribute> attributes) {
		Set<Attribute> newSet = new HashSet<Attribute>();
		attributes.forEach(attribute -> {
			Attribute savedAttribute = createAttributeIfNotExist(attribute.getAttrName(),
					attribute.getAttrValue());
			newSet.add(savedAttribute);
		});
		return newSet;
	};

	private Attribute createAttributeIfNotExist(String name, String value) {
		Attribute attributeFromRepo = serverAttributeRepo.findByAttrNameAndAttrValue(name, value);
		if (attributeFromRepo == null) {
			Attribute newAttribute = new Attribute(name, value);
			return save(serverAttributeRepo, newAttribute);
		}
		return attributeFromRepo;
	}

	private Country createCountryIfNotExist(Server server) {
		String name = server.getCountry().getName();
		Country countryFromRepo = countryRepo.findByName(name);
		if (countryFromRepo == null) {
			Country newCountry = new Country(name);
			return save(countryRepo, newCountry);
		}
		return countryFromRepo;
	}

	private Perimeter createPerimeterIfNotExist(Server server) {
		String name = server.getPerimeter().getName();
		Perimeter perimeterFromRepo = perimeterRepo.findByName(name);
		if (perimeterFromRepo == null) {
			Perimeter newPerimeter = new Perimeter(name);
			return save(perimeterRepo, newPerimeter);
		}
		return perimeterFromRepo;
	}

	private Ip createIpIfNotExist(Server server) {
		String ip = server.getIp().getIp();
		Ip ipFromRepo = ipRepo.findByIp(ip);
		if (ipFromRepo == null) {
			Ip newIp = new Ip(ip);
			return save(ipRepo, newIp);
		}
		return ipFromRepo;
	}

	private static <T> T save(JpaRepository<T, Long> repo, T entity) {
		return repo.save(entity);
	}

}
