package br.com.ahrpius.respect.jvalidation;

public enum Mode {
	DEFAULT("Data validation failed for %s"),
	NEGATIVE("Data validation failed for %s");
	private String text;
	public String getText() {
		return text;
	}
	private Mode(String text) {
		this.text = text;
	}
}
