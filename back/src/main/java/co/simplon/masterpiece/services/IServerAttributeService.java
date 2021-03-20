package co.simplon.masterpiece.services;

import java.util.List;

import co.simplon.masterpiece.dtos.AttributeSelectOptionsViewDto;

public interface IServerAttributeService {

	AttributeSelectOptionsViewDto getAllValues();

	List<String> getAttrValues(String attrName);
}
