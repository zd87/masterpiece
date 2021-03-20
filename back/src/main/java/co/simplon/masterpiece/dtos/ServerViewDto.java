package co.simplon.masterpiece.dtos;

import java.util.List;

public interface ServerViewDto {

	Long getId();

	String getName();

	String getFullName();

	IpViewDto getIp();

	CountryViewDto getCountry();

	PerimeterViewDto getPerimeter();

	List<AttributeViewDto> getAttributes();

}
