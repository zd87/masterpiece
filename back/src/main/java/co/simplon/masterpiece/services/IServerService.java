package co.simplon.masterpiece.services;

import java.util.List;

import co.simplon.masterpiece.dtos.ServerDto;
import co.simplon.masterpiece.dtos.ServerViewDto;

public interface IServerService {

	List<ServerViewDto> getAll();

	void create(ServerDto serverDto);

	void update(Long id, ServerDto serverDto);

	void deleteById(Long id);

	void saveAll(List<ServerDto> serverDtos);
}
