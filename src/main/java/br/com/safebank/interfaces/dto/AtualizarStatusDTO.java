package br.com.safebank.interfaces.dto;

import br.com.safebank.domain.enums.TipoStatusPagamento;

public record AtualizarStatusDTO(
        Long idPagamento,
        TipoStatusPagamento status) {}
