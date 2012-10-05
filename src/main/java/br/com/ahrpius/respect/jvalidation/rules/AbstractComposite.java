package br.com.ahrpius.respect.jvalidation.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ahrpius.respect.jvalidation.Validatable;

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
    
    protected  List<Exception> validateRules(Object input) {
        List<Validatable> validators = this.getRules();
        List<Exception> exceptions = new ArrayList<Exception>();
        for (Validatable v : validators) {
			try {
				v.assertThat(input);
			} catch (Exception e) {
				exceptions.add(e);
			}
		}
        return exceptions;
    }

}
