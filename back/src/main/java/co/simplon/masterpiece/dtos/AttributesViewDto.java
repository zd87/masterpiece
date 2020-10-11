package co.simplon.masterpiece.dtos;

import java.util.List;

public class AttributesViewDto {

	private List<String> names;

	private List<String> values;

	public AttributesViewDto(List<String> names, List<String> values) {
		this.names = names;
		this.values = values;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
