package br.com.challenge.financeiro.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.challenge.financeiro.model.enums.CategoriaDespesa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "despesas")
public class Despesa extends Operacao {
	private CategoriaDespesa categoria = CategoriaDespesa.OUTRAS;
}