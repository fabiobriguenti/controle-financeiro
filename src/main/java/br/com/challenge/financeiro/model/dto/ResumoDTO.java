package br.com.challenge.financeiro.model.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.challenge.financeiro.model.enums.CategoriaDespesa;
import lombok.Data;

@Data
public class ResumoDTO {
	private BigDecimal receita = BigDecimal.ZERO;
	private BigDecimal despesa = BigDecimal.ZERO;
	private BigDecimal saldo = BigDecimal.ZERO;
	private Map<CategoriaDespesa, BigDecimal> gastos = new ConcurrentHashMap<>();
}