package br.com.ahrpius.respect.jvalidation.exceptions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExceptionIterator implements Iterator<ValidationException>{
	
	private List<ValidationException> exceptions;
	private ValidationException current;
	private Boolean fullRelated;

	public ExceptionIterator(List<ValidationException> exceptions){
		this(exceptions, false);
	}
	
	public ExceptionIterator(ValidationException validationException, boolean fullRelated){
		this(Arrays.asList(validationException), fullRelated);
	}
	
	public ExceptionIterator(List<ValidationException> exceptions, boolean fullRelated){
		this.exceptions = exceptions;
		this.fullRelated = fullRelated;
	}

	@Override
	public boolean hasNext() {
		//TODO unhappy with this thing, write better! BETTER!
		if (current==null)
			current = exceptions.get(0);		
		if (current instanceof AbstractNestedException)
			return !((AbstractNestedException)current).getRelateds(fullRelated).isEmpty();
		
			return false;
	}

	@Override
	public AbstractNestedException next() {
		ExceptionIterator iterator = new ExceptionIterator(((AbstractNestedException)current).getRelateds(fullRelated), fullRelated);
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
