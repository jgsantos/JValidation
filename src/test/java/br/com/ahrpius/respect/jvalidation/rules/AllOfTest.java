package br.com.ahrpius.respect.jvalidation.rules;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import br.com.ahrpius.respect.jvalidation.Validatable;
import br.com.ahrpius.respect.jvalidation.rules.AllOf;
import br.com.ahrpius.respect.jvalidation.rules.Bool;
import br.com.ahrpius.respect.jvalidation.rules.Hexa;


public class AllOfTest {
	
	@Test
    public void test_removeRules_should_remove_all_rules(){
        AllOf o = new AllOf();
        o.addRules(new Bool(), new Hexa());
        o.removeRules();
        
        assertEquals(0, o.getRules().size());
    }
	
	@Test
	public void test_addRules_using_array_of_rules(){
		AllOf o = new AllOf();
		Validatable x;
		//Validatable[] a = ;
        o.addRules(
        		x = new Bool(), new Hexa()
        );
        assertTrue(o.hasRule(x));
        assertTrue(!o.hasRule(new Hexa()));
    }
	
}
