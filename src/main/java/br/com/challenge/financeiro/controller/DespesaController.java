package br.com.challenge.financeiro.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.financeiro.model.entity.Despesa;
import br.com.challenge.financeiro.repository.DespesaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/despesas", produces = MediaType.APPLICATION_NDJSON_VALUE)
public class DespesaController {
	
	@Autowired
	private DespesaRepository repository;
	
	@GetMapping
	public Flux<Despesa> listar(@RequestParam(required = false, defaultValue = "") String descricao) {
		return repository.findAll().switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
	
	@PostMapping
	public Mono<Despesa> cadastrar(@RequestBody Despesa despesa) {
		return repository.insert(despesa);
	}
	
	@GetMapping("/{id}")
	public Mono<Despesa> detalhar(@PathVariable String id) {
		return repository.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
	
	@PutMapping("/{id}")
	public Mono<Despesa> atualizar(@PathVariable String id, @RequestBody Despesa despesa) {
		 return repository.findById(id)
			.switchIfEmpty(Mono.error(new NoSuchElementException()))
			.flatMap(registro -> {
				despesa.setId(registro.getId());
				return repository.save(despesa);
			});
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return repository.findById(id)
			.switchIfEmpty(Mono.error(new NoSuchElementException()))
			.flatMap(repository::delete);
	}
	
	@GetMapping("/{ano}/{mes}")
	public Flux<Despesa> listar(@PathVariable Integer ano, @PathVariable Integer mes) {
		return repository.findByAnoMes(ano, mes).switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
}