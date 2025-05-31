package com.locadora.exata.controller;

import com.locadora.exata.model.Equipamento;
import com.locadora.exata.service.CategoriaService;
import com.locadora.exata.service.EquipamentoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("equipamentos", equipamentoService.listar());
        return "equipamento-listagem";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("equipamento", new Equipamento());
        model.addAttribute("categorias", categoriaService.listar());
        return "equipamento-cadastro";
    }

    @PostMapping("/gravar")
    public String gravar(@Valid @ModelAttribute Equipamento equipamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "equipamento-cadastro";
        }
        equipamentoService.cadastrar(equipamento);
        return "redirect:/equipamentos/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Equipamento> equipamento = equipamentoService.obter(id);
        if (equipamento.isEmpty()) {
            return "redirect:/equipamentos/lista"; // ou uma página de erro
        }
        model.addAttribute("equipamento", equipamento.get());
        model.addAttribute("categorias", categoriaService.listar());
        return "equipamento-cadastro";
    }

    @GetMapping("/disponiveis")
    public String listarDisponiveis(Model model) {
        model.addAttribute("equipamentos", equipamentoService.listarDisponiveis());
        return "equipamentos-lista";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        equipamentoService.remover(id);
        return "redirect:/equipamentos/lista";
    }

}
