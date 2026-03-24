package br.com.safebank.domain.model;

import br.com.safebank.domain.enums.MetodoPagamento;
import br.com.safebank.domain.enums.TipoStatusPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "pagamentos")
@Entity(name = "Pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigoDebito;
    private String cpfCnpj;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    @ManyToOne
    @JoinColumn(name = "status_pagamento_pk")
    private StatusPagamento status;
    private String numeroCartao;
    private Double valor;
    private Boolean ativo = true;


    public void atualizarStatus(StatusPagamento novoStatus) {

        TipoStatusPagamento atual = this.status.getTipo();
        TipoStatusPagamento novo = novoStatus.getTipo();

        if (atual == TipoStatusPagamento.SUCESSO) {
            throw new RuntimeException("Pagamento já processado com sucesso.");
        }

        if (atual == TipoStatusPagamento.PENDENTE &&
                novo != TipoStatusPagamento.SUCESSO &&
                novo != TipoStatusPagamento.FALHA) {
            throw new IllegalArgumentException("Transição inválida.");
        }

        if (atual == TipoStatusPagamento.FALHA &&
                novo != TipoStatusPagamento.PENDENTE) {
            throw new IllegalArgumentException("Transição inválida.");
        }

        this.status = novoStatus;
    }

    public void inativarPagamento() {
        if (this.status.getTipo() != TipoStatusPagamento.PENDENTE) {
            throw new RuntimeException("Só pode excluir pagamento pendente.");
        }
        this.ativo = false;
    }
}
