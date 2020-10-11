package co.simplon.masterpiece.services;

import co.simplon.masterpiece.dtos.ServerDto;

public interface IServerService {
//DELETE IF TESTS WELL
//	ServerDto getOne(Long id);

	void create(ServerDto serverDto);

	void update(Long id, ServerDto serverDto);

	void deleteById(Long id);
}
