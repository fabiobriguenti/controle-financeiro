package br.com.challenge.financeiro.model.dto;

import java.math.BigDecimal;

import br.com.challenge.financeiro.model.enums.CategoriaDespesa;
import lombok.Data;

@Data
public class GastoDTO {
	private CategoriaDespesa categoria;
	private BigDecimal valor;
}