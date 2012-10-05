package br.com.ahrpius.respect.jvalidation.rules;

import static junit.framework.Assert.*;

import org.junit.Test;

import br.com.ahrpius.respect.jvalidation.Validatable;
import br.com.ahrpius.respect.jvalidation.exceptions.ValidationException;
import br.com.ahrpius.respect.jvalidation.rules.Bool;

public class BoolTest {
	
	@Test
	public void test_boolean_values_ONLY_should_return_true() throws ValidationException {
		Validatable v = new Bool();
		
		assertTrue(v.validate("true"));
		assertTrue(v.validate("false"));
		assertTrue(v.assertThat("true"));
		assertTrue(v.assertThat("false"));
		assertTrue(v.check("true"));
		assertTrue(v.check("false"));
		
		assertTrue(v.validate(Boolean.TRUE));
		assertTrue(v.validate(Boolean.FALSE));
		assertTrue(v.assertThat(Boolean.TRUE));
		assertTrue(v.assertThat(Boolean.FALSE));
		assertTrue(v.check(Boolean.TRUE));
		assertTrue(v.check(Boolean.FALSE));
		
		assertTrue(v.validate(true));
		assertTrue(v.validate(false));
		assertTrue(v.assertThat(true));
		assertTrue(v.assertThat(false));
		assertTrue(v.check(true));
		assertTrue(v.check(false));
	}

}
