package br.com.safebank.interfaces.controller;

import br.com.safebank.domain.enums.TipoStatusPagamento;
import br.com.safebank.domain.model.StatusPagamento;
import br.com.safebank.domain.service.PagamentoService;
import br.com.safebank.infra.repository.PagamentoRepository;
import br.com.safebank.interfaces.dto.AtualizarStatusDTO;
import br.com.safebank.interfaces.dto.DadosPagamentoRequestDTO;
import br.com.safebank.interfaces.dto.DadosPagamentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pagamento")
class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<DadosPagamentoResponseDTO> criar(@RequestBody DadosPagamentoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<DadosPagamentoResponseDTO>> listar(
            @RequestParam(required = false) Integer codigoDebito,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) TipoStatusPagamento status) {

        return ResponseEntity.ok(service.listar(codigoDebito, cpfCnpj, status));
    }

    @PutMapping("/status")
    public ResponseEntity<Void> atualizarStatus(@RequestBody AtualizarStatusDTO dto) {
        service.atualizarStatus(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}