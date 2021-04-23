package co.simplon.masterpiece.dtos;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import co.simplon.masterpiece.errorHandling.UniqueServerFullName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@UniqueServerFullName
public class ServerDto {

	private Long id;

	@NotBlank
	@Size(max = 45)
	private String name;

	@NotBlank
	@Size(max = 45)
	private String fullName;

	@Valid
	private IpDto ip;

	@Valid
	private CountryDto country;

	@Valid
	private PerimeterDto perimeter;

	private List<@Valid AttributeDto> attributes;

}
