package br.com.ahrpius.respect.jvalidation.rules;

public class Between extends AllOf {
	
	public Between(Number minValue, Number maxValue, boolean inclusive){
		
		if (minValue==null) 
			throw new IllegalArgumentException("");
		
		if (maxValue==null) 
			throw new IllegalArgumentException("");
		
		if (minValue.doubleValue() > maxValue.doubleValue())
			throw new IllegalArgumentException(
					String.format("%s cannot be less than  %s for validation", minValue, maxValue)
			);
		
		addRule(new Min(minValue, inclusive));
		addRule(new Max(maxValue, inclusive));
	}

}
