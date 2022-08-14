package br.com.challenge.financeiro.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.challenge.financeiro.model.entity.Despesa;
import reactor.core.publisher.Flux;

public interface DespesaRepository extends ReactiveMongoRepository<Despesa, String> {
	Flux<Despesa> findByDescricaoLike(String descricao);
	
	@Query("{'data.year': ?0, 'data.month': ?1}")
	Flux<Despesa> findByAnoMes(Integer ano, Integer mes);
}