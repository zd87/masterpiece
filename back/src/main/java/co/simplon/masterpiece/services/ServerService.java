package co.simplon.masterpiece.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.dtos.ServerAttributeDto;
import co.simplon.masterpiece.entities.Server;
import co.simplon.masterpiece.entities.ServerAttribute;
import co.simplon.masterpiece.repositories.ServerAttributeRepository;
import co.simplon.masterpiece.repositories.ServerRepository;

@Service
public class ServerService implements IServerService {

	private final ServerRepository serverRepo;

	private final ServerAttributeRepository serverMetaRepo;

	public ServerService(ServerRepository serverRepo,
			ServerAttributeRepository serverMetaRepo) {
		this.serverRepo = serverRepo;
		this.serverMetaRepo = serverMetaRepo;
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

	public Server create(ServerDto serverDto) {
		/* creating set of attributes */
		Set<ServerAttribute> attributes = new HashSet<ServerAttribute>();
		for (ServerAttributeDto attr : serverDto.getAttributes()) {
			serverMetaRepo.createIfNotExist(attr.getAttrName(), attr.getAttrValue());
			ServerAttribute savedAttribute = serverMetaRepo.getOne(attr.getAttrName(),
					attr.getAttrValue());
			attributes.add(savedAttribute);
		}
		/* composing a Server */
		Server newServer = new Server(serverDto.getName(), serverDto.getFullName(),
				serverDto.getIp(), serverDto.getCountry(), serverDto.getPerimeter(),
				attributes);
		serverRepo.save(newServer);
		return newServer;
	}

	public List<Server> getAll() {
		return serverRepo.findAll();
	}

}
