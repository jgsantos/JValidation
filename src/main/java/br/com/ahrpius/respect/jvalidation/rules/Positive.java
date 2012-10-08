package br.com.ahrpius.respect.jvalidation.rules;


public class Positive extends AbstractRule {

	@Override
	public Boolean validate(Object input) {
		
		if (input==null)
			return Boolean.FALSE;
		else if ( input instanceof Number) {
			Number number = (Number) input;
			return number.doubleValue() > 0;
		}
		return null;
		
	}
	
	
}
