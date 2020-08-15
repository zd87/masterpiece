package co.simplon.masterpiece.dtos;

import java.util.List;

public class ServerAttributesViewDto {

	private List<String> names;

	private List<String> values;

	public ServerAttributesViewDto(List<String> names, List<String> values) {
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
