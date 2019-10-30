package br.com.sib.financeiro.service.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sib.financeiro.config.validacao.ErroFormularioDto;
import br.com.sib.financeiro.modelo.Categoria;
import br.com.sib.financeiro.modelo.form.CategoriaForm;
import br.com.sib.financeiro.repositores.CategoriaRepository;

public class CategoriaInsertValidator implements ConstraintValidator<CategoriaInsert,CategoriaForm> {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void initialize(CategoriaInsert ann) {
	}

	@Override
	public boolean isValid(CategoriaForm form, ConstraintValidatorContext context) {
		
		List<ErroFormularioDto> list = new ArrayList<>();
		
		Categoria categoria = this.categoriaRepository.findByCodigo(form.getCodigo());
		if(categoria != null) {
			list.add(new ErroFormularioDto("codigo","Esse codigo já existe!"));
		}
		
		
		for (ErroFormularioDto e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
