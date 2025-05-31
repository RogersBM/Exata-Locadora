package com.locadora.exata.service;

import com.locadora.exata.model.Cargo;
import com.locadora.exata.model.CargoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<Cargo> listar() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> obter(Integer id) {
        return cargoRepository.findById(id);
    }

    public Cargo cadastrar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public Cargo atualizar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public void remover(Integer id) {
        cargoRepository.deleteById(id);
    }
}
