package br.com.ahrpius.respect.jvalidation.rules;

import java.util.HashMap;
import java.util.List;

import br.com.ahrpius.respect.jvalidation.Validatable;
import br.com.ahrpius.respect.jvalidation.Validators;
import br.com.ahrpius.respect.jvalidation.exceptions.AbstractNestedException;
import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;

public class AllOf extends AbstractComposite {
	
	public AllOf(Validators... v){
		this.addRules(v);
	}
	
	@Override
	public Boolean assertThat(Object input) throws ValidationException {
		
		List<ValidationException> exceptions = this.validateRules(input);
		int numRules = rules.size();
		int numExceptions = exceptions.size();
		
		HashMap<String, String> summary = new HashMap<String, String>();
		summary.put("total", String.valueOf( numRules ));
		summary.put("failed", String.valueOf( numExceptions ));
		summary.put("passed", String.valueOf( numRules-numExceptions ));
        
        if (!exceptions.isEmpty()) {
        	this.clazz = AllOf.class;
        	throw ((AbstractNestedException)reportError(input, summary)).setRelated(exceptions); 
        }
            
        return Boolean.TRUE;
    }

	@Override
    public Boolean check(Object input) throws ValidationException {
		for (Validatable v : rules) {
			if (!v.check(input))
				return Boolean.FALSE;
		}
		return Boolean.TRUE;
    }

	@Override
    public Boolean validate(Object input) {
		for (Validatable v : rules) {
			if (!v.validate(input))
				return Boolean.FALSE;
		}
		return Boolean.TRUE;		

    }
}
