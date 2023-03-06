package br.com.alura.food.pagamentos.service;

import br.com.alura.food.pagamentos.domain.Pagamento;
import br.com.alura.food.pagamentos.domain.enuns.Status;
import br.com.alura.food.pagamentos.dto.PagamentoDto;
import br.com.alura.food.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return pagamentoRepository.findAll(paginacao).map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorID(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        Optional<Pagamento> buscarBanco = pagamentoRepository.findById(id);
        if (buscarBanco.isPresent()){
            Pagamento retorno = pagamentoRepository.save(this.atualizaDados(pagamento, buscarBanco));

            return modelMapper.map(retorno, PagamentoDto.class);
        }

        return null;
    }

    private Pagamento atualizaDados(Pagamento dados, Optional<Pagamento> atualizar) {
        Pagamento retorno = new Pagamento();

        retorno.setId(dados.getId() != null ? dados.getId() : atualizar.get().getId());
        retorno.setValor(dados.getValor() != null ? dados.getValor() : atualizar.get().getValor());
        retorno.setNome(dados.getNome() != null ? dados.getNome() : atualizar.get().getNome());
        retorno.setNumero(dados.getNumero() != null ? dados.getNumero() : atualizar.get().getNumero());
        retorno.setExpiracao(dados.getExpiracao() != null ? dados.getExpiracao() : atualizar.get().getExpiracao());
        retorno.setCodigo(dados.getCodigo() != null ? dados.getCodigo() : atualizar.get().getCodigo());
        retorno.setStatus(dados.getStatus() != null ? dados.getStatus() : atualizar.get().getStatus());
        retorno.setPedidoId(dados.getPedidoId() != null ? dados.getPedidoId() : atualizar.get().getPedidoId());
        retorno.setFormaDePagamentoId(dados.getFormaDePagamentoId() != null ? dados.getFormaDePagamentoId() : atualizar.get().getFormaDePagamentoId());

        return retorno;
    }
}
