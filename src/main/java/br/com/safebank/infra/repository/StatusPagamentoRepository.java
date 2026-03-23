package br.com.safebank.infra.repository;

import br.com.safebank.domain.enums.TipoStatusPagamento;
import br.com.safebank.domain.model.Pagamento;
import br.com.safebank.domain.model.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusPagamentoRepository extends JpaRepository<StatusPagamento, Long> {
    Optional<StatusPagamento> findByTipo(TipoStatusPagamento tipo);
}
