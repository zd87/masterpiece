package co.simplon.masterpiece.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.ServerAttributeDto;
import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.entities.Server;
import co.simplon.masterpiece.entities.ServerAttribute;
import co.simplon.masterpiece.repositories.ServerAttributeRepository;
import co.simplon.masterpiece.repositories.ServerRepository;

@Service
public class ServerService implements IServerService {

	private final ServerRepository servers;

	private final ServerAttributeRepository serverAttributes;

	public ServerService(ServerRepository servers,
			ServerAttributeRepository serverAttributes) {
		this.servers = servers;
		this.serverAttributes = serverAttributes;
	}

	public ServerDto getOne(Long id) {
		/*
		 * Optional<Server> serverOptional = serverRepo.findById(id);
		 * List<ServerMetaViewDto> attributes =
		 * serverMetaRepo.getAttributesByServerId(id);
		 */
		/*
		 * if (serverOptional.isPresent()) { Server server = serverOptional.get(); }
		 */
		/* return new ServerDto(serverOptional.get(), attributes); */
		return null;
	}

	public List<Server> getAll() {
		return servers.findAll();
	}

	public void create(ServerDto serverDto) {
		/* creating set of attributes */
		Set<ServerAttribute> attributes = saveServerAttributes(serverDto.getAttributes());

		/* composing a Server */
		Server newServer = new Server(serverDto.getName(), serverDto.getFullName(),
				serverDto.getIp(), serverDto.getCountry(), serverDto.getPerimeter(),
				attributes);
		servers.save(newServer);
	}

	public void update(Long id, @Valid ServerDto serverDto) {
		/* creating set of attributes */
		Set<ServerAttribute> attributes = saveServerAttributes(serverDto.getAttributes());

		/* updating server */
		Server serverToUpdate = servers.getOne(id);
		System.out.println(serverToUpdate);
		serverToUpdate.setServer(serverDto.getName(), serverDto.getFullName(),
				serverDto.getIp(), serverDto.getCountry(), serverDto.getPerimeter(),
				attributes);
		servers.save(serverToUpdate);
		System.out.println(serverToUpdate);
	}

	public Set<ServerAttribute> saveServerAttributes(
			List<ServerAttributeDto> attributes) {
		Set<ServerAttribute> returnAttributes = new HashSet<ServerAttribute>();
		for (ServerAttributeDto attr : attributes) {
			serverAttributes.createIfNotExist(attr.getAttrName(), attr.getAttrValue());
			ServerAttribute savedAttribute = serverAttributes.getOne(attr.getAttrName(),
					attr.getAttrValue());
			returnAttributes.add(savedAttribute);
			System.out.println("Saved attribute" + savedAttribute);
		}
		return returnAttributes;
	}

	public void deleteById(Long id) {
		servers.deleteById(id);
	}

}
