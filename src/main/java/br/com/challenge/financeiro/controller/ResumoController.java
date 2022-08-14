package br.com.challenge.financeiro.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.financeiro.model.dto.ResumoDTO;
import br.com.challenge.financeiro.service.ResumoService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/resumo", produces = MediaType.APPLICATION_NDJSON_VALUE)
public class ResumoController {
	
	@Autowired
	private ResumoService resumoService;
	
	@GetMapping("/{ano}/{mes}")
	public Mono<ResumoDTO> listar(@PathVariable Integer ano, @PathVariable Integer mes) {
		return resumoService.calculaResumo(ano, mes).switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
}