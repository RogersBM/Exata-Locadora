package com.locadora.exata.controller;

import com.locadora.exata.model.Categoria;
import com.locadora.exata.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "categoria-listagem";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria-cadastro";
    }

    @PostMapping("/gravar")
    public String gravar(@Valid @ModelAttribute Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Erro de validação ao salvar categoria: " + result.getAllErrors());
            model.addAttribute("categoria", categoria);
            return "categoria-cadastro";
        }

        try {
            categoriaService.cadastrar(categoria);
        } catch (IllegalArgumentException ex) {
            result.reject("error.categoria", ex.getMessage());
            return "categoria-cadastro";
        }

        return "redirect:/categorias/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        categoriaService.remover(id);
        return "redirect:/categorias/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Categoria> categoria = categoriaService.obter(id);
        model.addAttribute("categoria", categoria.orElse(new Categoria()));
        return "categoria-cadastro";
    }
}
