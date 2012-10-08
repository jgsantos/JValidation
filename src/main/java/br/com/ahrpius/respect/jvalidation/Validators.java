package br.com.ahrpius.respect.jvalidation;

public interface Validators extends Validatable {
	Validators bool();
	Validators hexa();
	Validators allOf(Validators... v);
	Validators positive();
}
