package br.com.ahrpius.respect.jvalidation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import br.com.ahrpius.respect.jvalidation.exceptions.AbstractNestedException;
import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;
import br.com.ahrpius.respect.jvalidation.rules.AllOf;


public class Validator extends AllOf implements InvocationHandler{
	
	public Validator(){
		super(null);
	}
	
	public Validator(Validators v) {
		super(v);
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] os)
			throws Throwable {

		if ( m.getDeclaringClass().isAssignableFrom(this.getClass()) ) {
			try {
				return m.invoke(this, os);
			} catch (InvocationTargetException e) {
                throw e.getTargetException();
			}
			
		}
		
		//TODO encapsular manipulação de string
		String name = m.getName();
		
		//TODO criar validação por objeto, string = fraco
		//TODO possivel validação por AbstractComposite?
		if ("allOf".equals(name)) 
			return proxy;
		
		name = "br.com.ahrpius.respect.jvalidation.rules."+name.substring(0,1).toUpperCase()+name.substring(1);

		Class<?> clazz = Class.forName(name);
		Constructor<?> constructors = clazz.getDeclaredConstructor(m.getParameterTypes());
		Validatable validatable = (Validatable) constructors.newInstance(os);
		this.addRule( validatable );

		return proxy;
	}

	//TODO create is a good name?
	public static Validators create(){
		
		return (Validators) Proxy.newProxyInstance(
				Validators.class.getClassLoader(),  
				new Class[] { Validators.class }, 
				new Validator()
				); 
	}

	public static void main(String[] args) {
		
		try {
			Validators v = Validator.create();
			v
				.positive()
				.positive()
				.assertThat(-1);
		} catch (ValidationException e) {
			if (e instanceof AbstractNestedException)
				System.err.println( ((AbstractNestedException)e).getFullMessage() );
			else
				System.err.println( e.getMessage() );
		}
		
	}

}
