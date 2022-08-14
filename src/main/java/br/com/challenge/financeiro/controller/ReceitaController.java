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

import br.com.challenge.financeiro.model.entity.Receita;
import br.com.challenge.financeiro.repository.ReceitaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/receitas", produces = MediaType.APPLICATION_NDJSON_VALUE)
public class ReceitaController {
	
	@Autowired
	private ReceitaRepository repository;
	
	@GetMapping
	public Flux<Receita> listar(@RequestParam(required = false, defaultValue = "") String descricao) {
		return repository.findByDescricaoLike(descricao).switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
	
	@PostMapping
	public Mono<Receita> cadastrar(@RequestBody Receita receita) {
		return repository.insert(receita);
	}
	
	@GetMapping("/{id}")
	public Mono<Receita> detalhar(@PathVariable String id) {
		return repository.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
	
	@PutMapping("/{id}")
	public Mono<Receita> atualizar(@PathVariable String id, @RequestBody Receita receita) {
		 return repository.findById(id)
			.switchIfEmpty(Mono.error(new NoSuchElementException()))
			.flatMap(registro -> {
				receita.setId(registro.getId());
				return repository.save(receita);
			});
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return repository.findById(id)
			.switchIfEmpty(Mono.error(new NoSuchElementException()))
			.flatMap(repository::delete);
	}
	
	@GetMapping("/{ano}/{mes}")
	public Flux<Receita> listar(@PathVariable Integer ano, @PathVariable Integer mes) {
		return repository.findByAnoMes(ano, mes).switchIfEmpty(Mono.error(new NoSuchElementException()));
	}
}