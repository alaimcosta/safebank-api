package br.com.safebank.interfaces.dto;

import br.com.safebank.domain.enums.MetodoPagamento;
import br.com.safebank.domain.model.Pagamento;

public record DadosPagamentoResponseDTO(
        Long id,
        Integer codigoDebito,
        String cpfCnpj,
        String status,
        Double valor
) {
    public DadosPagamentoResponseDTO(Pagamento pg) {
        this(
                pg.getId(),
                pg.getCodigoDebito(),
                pg.getCpfCnpj(),
                pg.getStatus().getTipo().name(),
                pg.getValor()
        );
    }
}