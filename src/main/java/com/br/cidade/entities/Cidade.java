package com.br.cidade.entities;

import java.util.List;

import com.br.endereco.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(
    name = "cidade",
    uniqueConstraints = @UniqueConstraint(columnNames = {"cid_nome","cid_uf"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "cid_nome",length = 200,nullable = false)
    @ToString.Include
    private String nome;

    @Column(name = "cid_uf",length = 2,nullable = false)
    private String uf;

    @PrePersist
    @PreUpdate
    private void formatarUf() {
        this.uf = this.uf != null ? this.uf.toUpperCase() : null;
    }

    @OneToMany(
        mappedBy = "cidade",
        cascade = CascadeType.ALL, 
        orphanRemoval  = true, 
        fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Endereco> enderecos;    
}
