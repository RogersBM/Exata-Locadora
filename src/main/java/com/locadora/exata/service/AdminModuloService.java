package com.locadora.exata.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminModuloService {

    public Map<String, Object> obterResumoAdmin() {
        Map<String, Object> resumo = new HashMap<>();
        resumo.put("clientesCadastrados", 25);
        resumo.put("alugueisAtivos", 10);
        return resumo;
    }
}
