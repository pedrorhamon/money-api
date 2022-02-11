package com.starking.moneyapi.exceptionhandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.*;

import org.springframework.web.bind.MethodArgumentNotValidException;
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
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemPadrao));
		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemPadrao), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> criarErros(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {			
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemPadrao = fieldError.toString();
			erros.add(new Erro(mensagemUsuario, mensagemPadrao));
		}
		return erros;
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
