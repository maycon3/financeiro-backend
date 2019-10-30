package br.com.sib.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sib.financeiro.modelo.Pessoa;
import br.com.sib.financeiro.service.HomeService;

@RestController
@RequestMapping("home")
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	@GetMapping
	public List<Pessoa> listaDeAniversariantes(){
		List<Pessoa> pessoas = homeService.aniversariantesDoMes();
		return pessoas;
	}
}
