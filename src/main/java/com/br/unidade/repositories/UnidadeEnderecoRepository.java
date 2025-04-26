package com.br.unidade.repositories;

import java.util.Optional;

import com.br.endereco.entities.Endereco;
import com.br.unidade.entities.Unidade;
import com.br.unidade.entities.UnidadeEndereco;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnidadeEnderecoRepository implements PanacheRepository<UnidadeEndereco> {

    public Optional<UnidadeEndereco> findByUnidade(Unidade unidade) {
        return find("unidade", unidade).firstResultOptional();
    }
    
    public Endereco findEnderecoByUnidade(Unidade unidade) {
        return find("unidade",unidade)
                    .firstResultOptional()
                    .map(UnidadeEndereco::getEndereco)
                    .orElse(null);
    }
    

}
