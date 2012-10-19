package br.com.ahrpius.respect.jvalidation.exceptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.ahrpius.respect.jvalidation.IMode;

public class ValidationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String id = "validation";
	private IMode mode = Mode.DEFAULT;
	private String name = "";
	protected String template = "";
	private Map<String, String> params = new HashMap<String, String>();
	private String message = this.getMessage();
    
    enum Mode implements IMode{
		DEFAULT("Data validation failed for %name"),
		NEGATIVE("Data validation failed for %name");

		private String text;
		@Override public String getText() {
			return text;
		}
		@Override public IMode getMode(IMode mode){
			return mode.getMode();
		}
		@Override public IMode getMode(){
			return this;
		}
		private Mode(String text) {
			this.text = text;
		}
    }

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
        this.setName(name);
        this.setParams(params);
        this.message  = getMainMessage();
        this.id = guessId();
        return this;
    }
	
	public String getMainMessage() {
		
		Map<String, String> sumary = this.params;
		sumary.put("name", this.name);
		String t = this.getTemplate();
		
//        params.put("name", this.name);
//        System.out.println(this.getClass());
//        System.out.println(params);
        return format(t, sumary);
        //this.getTemplate() + " " + this.getClass().getSimpleName();
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
	
	public ValidationException setMode(IMode mode) {
        this.mode = mode;
        this.template = this.buildTemplate();
        return this;
    }
	
	public String getTemplate() {
        return t();
    }
	public String t(){
        if (template!=null && template.length()>0)
            return template;
        else
            return template = mode.getText();
	}
	public String buildTemplate(){
		return chooseTemplate().getText();
	}
	
    public static String format(String template, Map<String, String> params) {
    	
    	for (String key : params.keySet()) {
			template = template.replaceAll("%"+key, params.get(key));
		}
    	
    	return template;
    }

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	public ValidationException setParams(Map<String, String> params){
		Set<String> set = params.keySet();
		for (String s : set) {
			this.setParam(s, params.get(s));
		}
		return this;
	}

	private ValidationException setParam(String key, String value) {
		//TODO rever translator
		this.params.put(key, stringify(value));
		return this;
	}

	public Map<String, String> getParams() {
		return this.params;
	}
	
	public String getParam(String param){
		return this.params.get(param);
	}
	
	public IMode chooseTemplate(){
		return this.mode;
	}
	public ValidationException setName(String name){
		this.name = stringify(name);
		return this;
	}
	public Boolean hasParam(String value){
		return params.containsKey(value); 
	}
	public ValidationException setId(String id){
		this.id = id;
		return this;
	}
    
}
