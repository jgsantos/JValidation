package br.com.ahrpius.respect.jvalidation.exceptions;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
		
		if (input==null)
			return "[null]";
		else if (input instanceof String)
			return (String) input;
		else if (input instanceof Collection) {
			Collection<?> is = (Collection<?>) input;
			StringBuffer sb = new StringBuffer();
			for (Object i : is) {
				sb.append( stringify(i) + ";" );
			}
			return sb.toString();
		}
		else 
			return stringifyObject(input);
	}
	
	public static String stringifyObject(Object input) {
		if (input instanceof Date || input instanceof Calendar) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format( input instanceof Calendar ? ((Calendar) input).getTime() : (Date) input );
		} else
			return input.toString();
	}
	
	public ValidationException configure(String name, Map<String, String> params) {
        this.name = name;
        this.params.putAll(params);
        this.message  = getMainMessage();
        this.id = guessId();
        return this;
    }
	
	public String getMainMessage() {
        params.put("name", this.name);

        //TODO
        return "mainMessage";//String.format(template, params.values());
    }
	
	@Override
	public String getMessage(){
		return this.message;
	}
	
	protected String guessId() {
        if ( this.id!=null && this.id.length()>0 && this.id != "validation")
            return this.id;
        //TODO encapsular manipulação de strings
        String name = getClass().getName().replace("Exception", "");
        return name.substring(0,1).toLowerCase()+name.substring(1);
    }

	public ValidationException setTemplate(String template) {
		this.template = template;
		return this;
	}

}
