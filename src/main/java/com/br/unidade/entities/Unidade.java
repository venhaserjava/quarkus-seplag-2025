package com.br.unidade.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "unidade",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "unid_nome"),
        @UniqueConstraint(columnNames = "unid_sigla")
    }
)
@ToString(onlyExplicitlyIncluded =  true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "unid_nome",nullable = false,length = 200)
    private String nome;

    @Column(name = "unid_sigla",nullable = false,length = 20)
    private String sigla;

}
