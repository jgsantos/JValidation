package br.com.ahrpius.respect.jvalidation.rules;

public class Min extends AbstractRule {

	private final double minValue;
	private final Boolean inclusive;

	public Min(Number minValue, Boolean inclusive){
		this.minValue = minValue.doubleValue();
		this.inclusive = inclusive;
	}
	
	@Override
	public Boolean validate(Object input){
		
		if (input==null || !(input instanceof Number))
			return Boolean.FALSE;
		
		Number n = (Number) input;
		
		if (inclusive) 
			return n.doubleValue() >= minValue;
		else 
			return n.doubleValue() > minValue;
			
	}
	
}
