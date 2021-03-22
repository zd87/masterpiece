package co.simplon.masterpiece.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttributeDto {

	@NotBlank
	@Size(max = 45)
	private String name;

	@NotBlank
	@Size(max = 255)
	private String value;

}
