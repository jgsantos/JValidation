package br.com.ahrpius.respect.jvalidation.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractNestedException extends ValidationException {

	private static final long serialVersionUID = 1L;
	
	public List<ValidationException> relateds = new ArrayList<ValidationException>();

	//	const ITERATE_TREE = 1;
	//    const ITERATE_ALL = 2;

	public AbstractNestedException addRelated(ValidationException related) {
		//TODO rever
		//        $this->related[spl_object_hash($related)] = $related;
		return this;
	}

	//TODO late
//	public function findMessages(array $paths) {
//		$messages = array();
//
//		foreach ($paths as $key => $value) {
//			$numericKey = is_numeric($key);
//			$path = $numericKey ? $value : $key;
//
//			$e = $this->findRelated($path);
//
//			if (is_object($e) && !$numericKey)
//				$e->setTemplate($value);
//
//				$path = str_replace('.', '_', $path);
//				$messages[$path] = $e ? $e->getMainMessage() : '';
//		}
//		return $messages;
//	}
//
//	public function findRelated($path)
//	{
//		$target = $this;
//		$path = explode('.', $path);
//
//		while (!empty($path) && $target !== false)
//			$target = $target->getRelatedByName(array_shift($path));
//			return $target;
//	}

	public ExceptionIterator getIterator(boolean full, String mode) {
		ExceptionIterator exceptionIterator = new ExceptionIterator(this, full);
		return exceptionIterator;
		
		//TODO may be 'll write those classes in java RecursiveIteratorIterator, RecursiveTreeIterator
//		if (mode.equals( "ITERATE_ALL" ))
//			return new RecursiveIteratorIterator($exceptionIterator, 1);
//		else
//			return new RecursiveTreeIterator($exceptionIterator);
	}

	public String getFullMessage() {
		List<String> messages = new ArrayList<String>();
		for (ExceptionIterator iterator = this.getIterator(false, ""); iterator.hasNext();) {
			AbstractNestedException exception = iterator.next();
			messages.add(exception.getMessage());
		}
		
		return Arrays.toString(messages.toArray());
	}

	//TODO why parameter?
	public List<ValidationException> getRelateds(boolean full) {
		return this.relateds;
	}

	public ValidationException getRelatedByName(String name) {
		
		for (ValidationException exception : relateds) {
			if (  name.equals( exception.getId() ) || name.equals( exception.getName() )  )
				return exception;
		}
		
		return null;
	}

	public AbstractNestedException setRelated(ValidationException... relatedExceptions) {
		relateds.addAll(  Arrays.asList( relatedExceptions )  );
		return this;
	}
	
	public AbstractNestedException setRelated(List<ValidationException> relatedExceptions) {
		relateds.addAll( relatedExceptions );
		return this;
	}

}
