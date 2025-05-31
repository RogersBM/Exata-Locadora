package com.locadora.exata.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class OperadorModuloService {

    public Map<String, Object> obterResumoOperador() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("clientesCadastrados", 25);
        resumo.put("alugueisPendentes", 5);
        return resumo;
    }
}
