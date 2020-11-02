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
		newServer.setIp(getIpFromRepo(newServer));
		newServer.setCountry(getCountryFromRepo(newServer));
		newServer.setPerimeter(getPerimeterFromRepo(newServer));
		newServer.setAttributes(getAttributesFromRepo(newServer.getAttributes()));
		return newServer;
	}

	private Set<Attribute> getAttributesFromRepo(Set<Attribute> attributes) {
		Set<Attribute> newSet = new HashSet<Attribute>();
		attributes.forEach(attribute -> {
			Attribute savedAttribute = createAttributeIfNotExist(attribute);
			newSet.add(savedAttribute);
		});
		return newSet;
	};

	private Attribute createAttributeIfNotExist(Attribute attribute) {
		String name = attribute.getAttrName();
		String value = attribute.getAttrValue();
		Attribute responseFromRepo = serverAttributeRepo.findByAttrNameAndAttrValue(name, value);
		return createIfNotExist(responseFromRepo, attribute, Attribute.class, serverAttributeRepo);
	}

	private Country getCountryFromRepo(Server server) {
		String name = server.getCountry().getName();
		Country responseFromRepo = countryRepo.findByName(name);
		return createIfNotExist(responseFromRepo, server.getCountry(), Country.class, countryRepo);
	}

	private Perimeter getPerimeterFromRepo(Server server) {
		String name = server.getPerimeter().getName();
		Perimeter responseFromRepo = perimeterRepo.findByName(name);
		return createIfNotExist(responseFromRepo, server.getPerimeter(), Perimeter.class,
				perimeterRepo);
	}

	private Ip getIpFromRepo(Server server) {
		String ip = server.getIp().getIp();
		Ip responseFromRepo = ipRepo.findByIp(ip);
		return createIfNotExist(responseFromRepo, server.getIp(), Ip.class, ipRepo);
	}

	private <T> T createIfNotExist(T responseFromRepo, T source, Class targetClass,
			JpaRepository<T, Long> repo) {
		if (responseFromRepo == null) {
			T entity = (T) mapper.map(source, targetClass);
			return repo.save(entity);
		}
		return responseFromRepo;
	}

}
