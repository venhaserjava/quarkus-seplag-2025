package com.br.endereco.entities;

import java.util.Set;

import com.br.cidade.entities.Cidade;
import com.br.unidade.entities.UnidadeEndereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    name = "endereco",
    uniqueConstraints = @UniqueConstraint(columnNames = {"end_logradouro","end_bairro","end_numero","cid_id"})
)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "end_tipo_logradouro",length = 50,nullable = false)
    @ToString.Include
    private String tipo_logradouro;

    @Column(name = "end_logradouro",length = 200,nullable = false)
    @ToString.Include
    private String logradouro;

    @Column(name = "end_numero",nullable = false)
    private Integer numero;

    @Column(name = "end_bairro",length = 100,nullable = false)
    private String bairro;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(
        name = "cid_id",
        referencedColumnName = "cid_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_endereco_cidade")
    )
    private Cidade cidade;    


    @OneToMany(mappedBy = "endereco")
    private Set<UnidadeEndereco> unidadeEnderecos;

}
