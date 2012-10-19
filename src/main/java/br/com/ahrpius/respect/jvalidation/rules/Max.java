package br.com.ahrpius.respect.jvalidation.rules;

public class Max extends AbstractRule {

	private final double maxValue;
	private final Boolean inclusive;

	public Max(Number maxValue, Boolean inclusive){
		this.maxValue = maxValue.doubleValue();
		this.inclusive = inclusive;
	}
	
	@Override
	public Boolean validate(Object input){
		
		if (input==null || !(input instanceof Number))
			return Boolean.FALSE;
		
		Number n = (Number) input;
		
		if (inclusive) 
			return n.doubleValue() <= maxValue;
		else 
			return n.doubleValue() < maxValue;
			
	}
	
}
