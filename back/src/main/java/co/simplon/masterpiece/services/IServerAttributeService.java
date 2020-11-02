package co.simplon.masterpiece.services;

import java.util.List;

import co.simplon.masterpiece.dtos.AttributesViewDto;

public interface IServerAttributeService {

	AttributesViewDto getAllValues();

	List<String> getAttrValues(String attrName);
}
