package br.com.sib.financeiro.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import br.com.sib.financeiro.modelo.exception.SaldoInsuficienteException;
import br.com.sib.financeiro.modelo.exception.ValorNegativoException;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;
import br.com.sib.financeiro.service.exception.PagamentoException;

@RestControllerAdvice
public class ErroDevalidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public MensagemDeErro handle(ObjectNotFoundException exception){		
		MensagemDeErro dto = new MensagemDeErro(exception.getLocalizedMessage());
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ValorNegativoException.class)
	public MensagemDeErro error(ValorNegativoException exception) {
		MensagemDeErro dto = new MensagemDeErro(exception.getLocalizedMessage());
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SaldoInsuficienteException.class)
	public MensagemDeErro saldoInsuficiente(SaldoInsuficienteException exception) {
		MensagemDeErro dto = new MensagemDeErro(exception.getLocalizedMessage());
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(PagamentoException.class)
	public MensagemDeErro contaJaPaga(PagamentoException exception) {
		MensagemDeErro dto = new MensagemDeErro(exception.getLocalizedMessage());
		return dto;
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception){
		List<ErroFormularioDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e,LocaleContextHolder.getLocale());
			ErroFormularioDto  erro = new ErroFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});		
		return dto;
	}
}
