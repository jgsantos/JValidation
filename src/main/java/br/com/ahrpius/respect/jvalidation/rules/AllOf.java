package br.com.ahrpius.respect.jvalidation.rules;

import java.util.List;

import br.com.ahrpius.respect.jvalidation.Validatable;
import br.com.ahrpius.respect.jvalidation.Validators;
import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;

public class AllOf extends AbstractComposite {
	
	public AllOf(Validators... v){
		this.addRules(v);
	}
	
	@Override
	public Boolean assertThat(Object input) {
		
		List<Exception> exceptions = this.validateRules(input);
		int numRules = rules.size();
		int numExceptions = exceptions.size();
		
		//TODO
//        $summary = array(
//            'total' => $numRules,
//            'failed' => $numExceptions,
//            'passed' => $numRules - $numExceptions
//        );
        
//        if (!exceptions.isEmpty())
//            throw this.reportError($input, $summary)->setRelated($exceptions);
            
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
