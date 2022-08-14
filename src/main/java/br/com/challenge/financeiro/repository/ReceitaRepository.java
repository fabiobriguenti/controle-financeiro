package br.com.challenge.financeiro.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.challenge.financeiro.model.entity.Receita;
import reactor.core.publisher.Flux;

public interface ReceitaRepository extends ReactiveMongoRepository<Receita, String> {
	Flux<Receita> findByDescricaoLike(String descricao);
	
	@Query("{'data.year': ?0, 'data.month': ?1}")
	Flux<Receita> findByAnoMes(Integer ano, Integer mes);
}