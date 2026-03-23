package br.com.safebank.interfaces.dto;

import br.com.safebank.domain.enums.MetodoPagamento;

public record DadosPagamentoRequestDTO(
        Integer codigoDebito,
        String cpfCnpj,
        MetodoPagamento metodoPagamento,
        String numeroCartao,
        Double valor
) {}