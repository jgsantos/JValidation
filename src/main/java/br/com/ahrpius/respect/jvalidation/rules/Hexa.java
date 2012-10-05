package br.com.ahrpius.respect.jvalidation.rules;

public class Hexa extends AbstractRule {

	@Override
	public Boolean validate(Object input) {
		if (input==null)
			return Boolean.FALSE;
		else if (input instanceof String) {
			String s = (String) input;
			try {
				Integer.parseInt(s, 16);
				return Boolean.TRUE;
			} catch (NumberFormatException e) {
				return Boolean.FALSE;
			}
		}
		
		return Boolean.FALSE;
	}
	
}
