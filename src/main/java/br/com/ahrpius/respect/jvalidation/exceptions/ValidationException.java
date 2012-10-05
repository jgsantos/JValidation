package br.com.ahrpius.respect.jvalidation.exceptions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String id = "validation";
//    protected $mode = self::MODE_DEFAULT;
	private String name = "";
	private String template = "";
    protected Map<String, String> params = new HashMap<String, String>();
	private String message = this.getMessage();

	public static String stringify(Object input) {
		if (input instanceof String)
			return input.toString();
		else if (input instanceof Collection) 
			return "Collection";
		else
			return input.toString();
	}
	
	public ValidationException configure(String name, Map<String, String> params) {
        this.name = name;
        this.params.putAll(params);
        this.message  = this.getMainMessage();
        this.id = this.guessId();
        return this;
    }
	
	public String getMainMessage() {
        params.put("name", this.name);

        //TODO
        return String.format(template, params.values());
    }
	
	@Override
	public String getMessage(){
		return this.message;
	}
	
	protected String guessId() {
        if ( this.id!=null && this.id.length()>0 && this.id != "validation")
            return this.id;
        //TODO encapsular manipulação de string
        String name = getClass().getName().replace("Exception", "id");
        return name.substring(0,1).toLowerCase()+name.substring(1);
    }

	public ValidationException setTemplate(String template) {
		this.template = template;
		return this;
	}

}
