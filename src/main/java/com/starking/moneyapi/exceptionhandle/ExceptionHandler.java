package com.starking.moneyapi.exceptionhandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemPadrao = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemPadrao), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {
		
		private String mensagemUsuario;
		private String mensagemPadrao;
		
		public Erro(String mensagemUsuario, String mensagemPadrao) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemPadrao = mensagemPadrao;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemPadrao() {
			return mensagemPadrao;
		}	
	}

}
