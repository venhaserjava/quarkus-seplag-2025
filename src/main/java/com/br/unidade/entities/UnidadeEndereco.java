package com.br.unidade.entities;

import com.br.endereco.entities.Endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidade_endereco")
//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "unid_id",referencedColumnName = "unid_id",nullable = false,unique = true)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "end_id",referencedColumnName = "end_id",nullable = false)
    private Endereco endereco;

}
