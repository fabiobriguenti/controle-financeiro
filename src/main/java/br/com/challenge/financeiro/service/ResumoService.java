package br.com.challenge.financeiro.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.financeiro.model.dto.ResumoDTO;
import br.com.challenge.financeiro.model.entity.Receita;
import br.com.challenge.financeiro.model.enums.CategoriaDespesa;
import br.com.challenge.financeiro.repository.DespesaRepository;
import br.com.challenge.financeiro.repository.ReceitaRepository;
import reactor.core.publisher.Mono;

@Service
public class ResumoService {
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	public Mono<ResumoDTO> calculaResumo(Integer ano, Integer mes) {		
		ResumoDTO resumoDTO = new ResumoDTO();
		
		return Mono.zip(
			montaReceitas(ano, mes), 
			montaDespesasComDetalhamentoGastos(ano, mes, resumoDTO.getGastos()))
		.map(tupla -> {
			resumoDTO.setReceita(tupla.getT1());
			resumoDTO.setDespesa(tupla.getT2());
			resumoDTO.setSaldo(tupla.getT1().subtract(tupla.getT2()));
			return resumoDTO;
		});		
	}

	private Mono<BigDecimal> montaReceitas(Integer ano, Integer mes) {
		return receitaRepository.findByAnoMes(ano, mes).map(Receita::getValor).reduce(BigDecimal.ZERO, (x1, x2) -> x1.add(x2));
	}

	private Mono<BigDecimal> montaDespesasComDetalhamentoGastos(Integer ano, Integer mes, Map<CategoriaDespesa, BigDecimal> map) {
		return despesaRepository.findByAnoMes(ano, mes).map(despesa -> {
			map.put(despesa.getCategoria(), map.getOrDefault(despesa.getCategoria(), BigDecimal.ZERO).add(despesa.getValor()));
			return despesa.getValor();
		}).reduce(BigDecimal.ZERO, (x1, x2) -> x1.add(x2));
	}
}