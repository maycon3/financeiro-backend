package br.com.sib.financeiro.controller.converte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConverteData {
	
	public String formatoComBarra(LocalDate data) {
		DateTimeFormatter formatoBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 return data.format(formatoBarra); 
		
	}

}
