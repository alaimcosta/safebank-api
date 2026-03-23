package br.com.safebank.domain.service;

import br.com.safebank.domain.enums.MetodoPagamento;
import br.com.safebank.domain.enums.TipoStatusPagamento;
import br.com.safebank.domain.model.Pagamento;
import br.com.safebank.domain.model.StatusPagamento;
import br.com.safebank.infra.repository.PagamentoRepository;
import br.com.safebank.infra.repository.StatusPagamentoRepository;
import br.com.safebank.interfaces.dto.AtualizarStatusDTO;
import br.com.safebank.interfaces.dto.DadosPagamentoRequestDTO;
import br.com.safebank.interfaces.dto.DadosPagamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private StatusPagamentoRepository statusRepository;


    public DadosPagamentoResponseDTO criar(DadosPagamentoRequestDTO dto) {

        if ((dto.metodoPagamento() == MetodoPagamento.CARTAO_CREDITO ||
                dto.metodoPagamento() == MetodoPagamento.CARTAO_DEBITO)
                && dto.numeroCartao() == null) {
            throw new RuntimeException("Número do cartão obrigatório.");
        }

        var statusInicial = statusRepository.findByTipo(TipoStatusPagamento.PENDENTE)
                .orElseThrow(() -> new RuntimeException("Status PENDENTE não encontrado."));

        var pagamento = new Pagamento();
        pagamento.setCodigoDebito(dto.codigoDebito());
        pagamento.setCpfCnpj(dto.cpfCnpj().replaceAll("\\D", ""));
        pagamento.setMetodoPagamento(dto.metodoPagamento());
        pagamento.setNumeroCartao(dto.numeroCartao());
        pagamento.setValor(dto.valor());
        pagamento.setStatus(statusInicial);

        repository.save(pagamento);

        return new DadosPagamentoResponseDTO(pagamento);
    }

    public void atualizarStatus(AtualizarStatusDTO dto) {
        var pagamento = repository.findById(dto.idPagamento())
                .orElseThrow();

        var novoStatus = statusRepository.findByTipo(dto.status())
                .orElseThrow(() -> new RuntimeException("Tipo de status não encontrado."));

        pagamento.atualizarStatus(novoStatus);

        repository.save(pagamento);
    }

    public List<DadosPagamentoResponseDTO> listar(
            Integer codigoDebito,
            String cpfCnpj,
            TipoStatusPagamento status) {

        List<Pagamento> lista;
        if (codigoDebito != null) {
            lista = repository.findByCodigoDebitoAndAtivoTrue(codigoDebito);
        } else if (cpfCnpj != null) {
            lista = repository.findByCpfCnpjAndAtivoTrue(cpfCnpj);
        } else if (status != null) {
            lista = repository.findByStatusTipoAndAtivoTrue(status);
        } else {
            lista = repository.findByAtivoTrue();
        }

        return lista.stream().map(DadosPagamentoResponseDTO::new).toList();
    }

    public void excluir(Long id) {
        var pagamento = repository.findById(id).orElseThrow();

        pagamento.inativarPagamento();
        repository.save(pagamento);
    }
}