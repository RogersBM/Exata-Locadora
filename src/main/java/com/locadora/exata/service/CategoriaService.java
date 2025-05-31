package com.locadora.exata.service;

import com.locadora.exata.model.Categoria;
import com.locadora.exata.model.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obter(int id) {
        return categoriaRepository.findById(id);
    }
    
    public Categoria cadastrar(Categoria categoria) {
        return categoriaRepository.save(categoria); // ✅ Este método estava ausente
    }    
               
    public Categoria atualizar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public void remover(Integer id) {
        categoriaRepository.deleteById(id);
    }

    
}