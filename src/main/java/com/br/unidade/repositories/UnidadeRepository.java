package com.br.unidade.repositories;

import com.br.unidade.entities.Unidade;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnidadeRepository implements PanacheRepository<Unidade> {
}
