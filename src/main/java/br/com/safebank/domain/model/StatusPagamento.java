package br.com.safebank.domain.model;

import br.com.safebank.domain.enums.TipoStatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "status_pagamento")
@Entity(name = "StatusPagamento")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusPagamento {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoStatusPagamento tipo;

    private String descricao;
}