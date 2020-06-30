package co.simplon.masterpiece.services;

import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.entities.Server;

public interface IServerService {

	ServerDto getOne(Long id);

	Server create(ServerDto serverDto);
}
