package com.locadora.exata.service;

import com.locadora.exata.exception.ResourceNotFoundException;
import com.locadora.exata.model.Locacao;
import com.locadora.exata.model.LocacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    public Locacao criarLocacao(Locacao locacao) {
        locacao.setId(null);
        locacao.setDataFim(null); // indica que ainda está em andamento
        locacao.setTotal(0.0);
        return locacaoRepository.save(locacao);
    }

    public Locacao buscarPorId(Integer id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação de código [" + id + "] não foi encontrada."));
    }

    @Transactional
    public Locacao encerrarLocacao(Integer id, LocalDate dataFim) {
        Locacao locacao = buscarPorId(id);
        locacao.setDataFim(dataFim);

        int dias = diferencaEmDias(locacao.getDataInicio(), dataFim);
        double valorDiaria = locacao.getEquip().getValor();
        double total = calcularValorTotal(valorDiaria, dias);

        locacao.setTotal(total); // Se existir campo "total" na entidade Locacao

        locacao.getEquip().setAlugado("N");

        return locacaoRepository.save(locacao);
    }

    public void excluirLocacao(Integer id) {
        Locacao locacao = buscarPorId(id);
        locacaoRepository.delete(locacao);
    }

    public List<Locacao> listarTodas() {
        return locacaoRepository.findAll();
    }

    public List<Locacao> listarAbertas() {
        return locacaoRepository.findByDataFimIsNull();
    }

    public int diferencaEmDias(LocalDate inicio, LocalDate fim) {
        Period periodo = Period.between(inicio, fim);
        int dias = periodo.getDays();
        return dias == 0 ? 1 : dias;
    }

    public double calcularValorTotal(double diaria, int dias) {
        return diaria * dias;
    }

    public List<Locacao> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return locacaoRepository.findByDataInicioBetween(dataInicio, dataFim);
    }
    
    public Optional<Locacao> obter(Integer id) {
        return locacaoRepository.findById(id);
    }

    public Locacao atualizar(Locacao locacao) {
        return locacaoRepository.save(locacao);
    }

}
