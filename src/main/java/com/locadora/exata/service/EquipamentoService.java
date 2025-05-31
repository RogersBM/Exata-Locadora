package com.locadora.exata.service;

import com.locadora.exata.model.Equipamento;
import com.locadora.exata.model.EquipamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public List<Equipamento> listar() {
        List<Equipamento> lista = equipamentoRepository.findAll();
        return (lista != null) ? lista : List.of(); // ou new ArrayList<>();
    }

    public Optional<Equipamento> obter(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Equipamento cadastrar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento atualizar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public void remover(Integer id) {
        equipamentoRepository.deleteById(id);
    }

    public List<Equipamento> listarDisponiveis() {
        return equipamentoRepository.findAll().stream()
                .filter(equipamento -> "N".equals(equipamento.getAlugado()))
                .toList();
    }
}
