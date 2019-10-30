package br.com.sib.financeiro.service.exception;

public class PagamentoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PagamentoException(String msg) {
		super(msg);
	}
	
	public PagamentoException(String msg,Throwable couse) {
		super(msg,couse);
	}

}
