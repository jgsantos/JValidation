package br.com.ahrpius.respect.jvalidation;

public interface Validators extends Validatable {
	Validators bool();
	Validators hexa();
	Validators allOf(Validators... v);
	Validators positive();
	Validators min(Number minValue, Boolean inclusive);
	Validators max(Number maxValue, Boolean inclusive);
	Validators between(Number minValue, Number maxValue, boolean inclusive);
}
