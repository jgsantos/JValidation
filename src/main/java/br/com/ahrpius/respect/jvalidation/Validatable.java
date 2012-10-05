package br.com.ahrpius.respect.jvalidation;

import java.util.Map;

import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;

public interface Validatable {
	
	Boolean assertThat(Object input) throws ValidationException;

	Boolean check(Object input) throws ValidationException;

	String getName();

	ValidationException reportError(Object input, Map<String, String> extraParams);

	Validatable setName(String name);

	Validatable setTemplate(String template);

	Boolean validate(Object input);
}
