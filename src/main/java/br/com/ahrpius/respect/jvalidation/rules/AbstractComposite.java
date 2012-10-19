package br.com.ahrpius.respect.jvalidation.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ahrpius.respect.jvalidation.Validatable;
import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;

public class AbstractComposite extends AbstractRule {
	
	protected List<Validatable> rules = new ArrayList<Validatable>();
	
	public AbstractComposite(){
		
	}
	
	public AbstractComposite addRule(Validatable validator){
		this.rules.add(validator);
		
		return this;
	}
	
    public void removeRules() {
        this.rules = new ArrayList<Validatable>();
    }
    
    public AbstractComposite addRules(Validatable... validators) {
    	
    	if (validators != null)
    		this.rules.addAll( Arrays.asList(validators) );
    	
        return this;
    }
    
    public List<Validatable> getRules(){
    	return rules;
    }
    
    public Boolean hasRule(Validatable validator) {
    	
    	if (rules.isEmpty()) return false;
    	
    	if (validator instanceof Validatable)
            return rules.contains(validator);
    	
        return Boolean.FALSE;
    }
    
    protected void appendRule(Validatable validator){
//TODO
    }
    
    protected  List<ValidationException> validateRules(Object input) {
    	
        List<ValidationException> exceptions = new ArrayList<ValidationException>();
        for (Validatable v : this.getRules()) {
//        	System.out.println("validateRules:"+v.getClass().getSimpleName());
			try {
				v.assertThat( input );
			} catch (ValidationException e) {
				exceptions.add(e);
			}
		}
        return exceptions;
    }

}
