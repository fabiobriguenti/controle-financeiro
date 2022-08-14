package br.com.challenge.financeiro.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Document(collection = "receitas")
@NoArgsConstructor
public class Receita extends Operacao {
	
}