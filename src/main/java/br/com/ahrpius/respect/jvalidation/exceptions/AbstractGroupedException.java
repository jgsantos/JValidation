package br.com.ahrpius.respect.jvalidation.exceptions;

import java.util.List;
import java.util.Map;

import br.com.ahrpius.respect.jvalidation.IMode;

public abstract class AbstractGroupedException extends AbstractNestedException {

	private ValidationException current;
	
	
	public IMode chooseTemplate() {
		//TODO
//        Integer numRules = Integer.valueOf( getParam("passed") );
//        int numFailed = this.relateds.size();
        return Mode.DEFAULT;// (numRules == numFailed) ? "none" : "some";
    }
	
	@Override
	public List<ValidationException> getRelateds(boolean full) {
		current = relateds.iterator().next();
        if (!full && 1 == relateds.size() && current instanceof AbstractNestedException)
                return ((AbstractNestedException) current).getRelateds(full);
            else
            	return this.relateds;		
	}
	
	@Override
	public Map<String, String> getParams() {
        if (1 == this.relateds.size())
            return current.getParams();
        else
            return super.getParams();
    }	
	
	@Override
    public String getTemplate() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
    	String parentTemplate = super.t();
    	Boolean isEmpty = (parentTemplate==null||parentTemplate.length()==0);

        if (!isEmpty && this.template != parentTemplate)
            return this.getTemplate();
        if (isEmpty && 1 == this.relateds.size())
            return current.getTemplate();
        else
            return parentTemplate;
    }	
}
