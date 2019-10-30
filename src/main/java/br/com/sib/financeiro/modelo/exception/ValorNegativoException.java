package br.com.sib.financeiro.modelo.exception;

public class ValorNegativoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValorNegativoException(String msg) {
		super(msg);
	}
	
	public ValorNegativoException(String msg, Throwable couse) {
		super(msg,couse);
	}
}
