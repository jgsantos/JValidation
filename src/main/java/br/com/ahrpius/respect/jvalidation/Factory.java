package br.com.ahrpius.respect.jvalidation;
import java.lang.reflect.Proxy;


public class Factory {

	public static Validators create(){
		Validator tratador = new Validator();
		 Class[] instancies = new Class[] {Validators.class};  
		  
         return (Validators) Proxy.newProxyInstance(Validators.class.getClassLoader(),  
                instancies, tratador); 

	}
}
