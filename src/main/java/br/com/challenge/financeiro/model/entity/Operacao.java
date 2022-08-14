package br.com.challenge.financeiro.model.entity;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Operacao {
	
	@Id
	private String id;
	private String descricao = "";
	private BigDecimal valor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/yyyy")
	private YearMonth data;
}