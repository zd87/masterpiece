package co.simplon.masterpiece.dtos;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttributeSelectOptionsViewDto {

	private HashMap<String, List<String>> attributes;

	private List<String> perimeters;

	private List<String> countries;

	private List<String> ips;

}
