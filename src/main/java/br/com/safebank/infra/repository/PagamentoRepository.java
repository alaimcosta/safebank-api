package br.com.safebank.infra.repository;

import br.com.safebank.domain.enums.TipoStatusPagamento;
import br.com.safebank.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAtivoTrue();
    List<Pagamento> findByCodigoDebitoAndAtivoTrue(Integer codigoDebito);
    List<Pagamento> findByCpfCnpjAndAtivoTrue(String cpfCnpj);
    List<Pagamento> findByStatusTipoAndAtivoTrue(TipoStatusPagamento status);
}
