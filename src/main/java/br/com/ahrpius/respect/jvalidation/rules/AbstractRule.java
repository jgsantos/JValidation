package br.com.ahrpius.respect.jvalidation.rules;

import java.util.HashMap;
import java.util.Map;

import br.com.ahrpius.respect.jvalidation.Validatable;
import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;

public class AbstractRule implements Validatable {
	
	protected String name = "";
	protected String template;
	protected Class<?> clazz = this.getClass();
	
	public AbstractRule(){}

	@Override
	public Boolean assertThat(Object input) throws ValidationException {
		
		if (validate(input))
			return Boolean.TRUE;
		else
			throw reportError(input, new HashMap<String, String>());//TODO rever segundo parametro
	}

	@Override
	public Boolean check(Object input) throws ValidationException {
		return this.assertThat(input);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Validatable setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public Validatable setTemplate(String template) {
		this.template = template;
		return this;		
	}

	@Override
	public Boolean validate(Object input) {
		return null;
	}

	@Override
	public ValidationException reportError(Object input, Map<String, String> extraParams) {
		
		ValidationException exception = this.createException();
		String i = ValidationException.stringify(input);
        String name = getName()!=null&&getName().length()>=0 ? getName() : "\""+i+"\"";
        
        Map<String, String> params = new HashMap<String, String>();
        
    	params.putAll(extraParams);
    	
        exception.configure(name, params);
        
        if (this.template!=null&&this.template.length()>0)
            exception.setTemplate( (this.template) );
        
        return exception;
	}
	
	protected ValidationException createException() {
		
		String className = this.clazz.getName();
		className = className.replace("rules", "exceptions");
		className += "Exception";
		//TODO encapsular reflection
		try {
			Class<?> clazz = Class.forName(className);
			Object newInstance = clazz.newInstance();
			if (newInstance instanceof ValidationException)
				return (ValidationException) newInstance;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return null;
    }

	
	

}
