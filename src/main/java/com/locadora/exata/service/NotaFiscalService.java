package com.locadora.exata.service;

import com.locadora.exata.model.NotaFiscal;
import com.locadora.exata.model.NotaFiscalRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public List<NotaFiscal> listar() {
        return Optional.ofNullable(notaFiscalRepository.findAll()).orElse(Collections.emptyList());
    }

    public Optional<NotaFiscal> obter(Integer id) {
        return notaFiscalRepository.findById(id);
    }

    public NotaFiscal cadastrar(NotaFiscal notaFiscal) {
        return notaFiscalRepository.save(notaFiscal);
    }

    public NotaFiscal atualizar(NotaFiscal notaFiscal) {
        return notaFiscalRepository.save(notaFiscal);
    }

    public void remover(Integer id) {
        notaFiscalRepository.deleteById(id);
    }
}
