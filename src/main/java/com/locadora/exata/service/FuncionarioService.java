package com.locadora.exata.service;

import com.locadora.exata.model.Funcionario;
import com.locadora.exata.model.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> obter(Integer id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario cadastrar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void remover(Integer id) {
        funcionarioRepository.deleteById(id);
    }
}
