package br.com.sib.financeiro.modelo.exception;

public class SaldoInsuficienteException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String msg) {
		super(msg);
	}
	
	public SaldoInsuficienteException(String msg, Throwable couse) {
		super(msg,couse);
	}
}
