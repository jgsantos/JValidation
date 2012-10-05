package br.com.ahrpius.respect.jvalidation.rules;

public class Bool extends AbstractRule {

	@Override
	public Boolean validate(Object input) {
		
		if (input==null)
			return Boolean.FALSE;
		if (input instanceof Boolean)
			return Boolean.TRUE;
		if (input instanceof String) {
			String stringVal = ( (String) input ).toLowerCase();
			return (  "true".equals( stringVal ) || "false".equals( stringVal )  ); 
		}
		return Boolean.FALSE;
	}	

}
