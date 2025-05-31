package com.locadora.exata.controller;

import com.locadora.exata.model.Cargo;
import com.locadora.exata.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/cargos")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("cargos", cargoService.listar());
        return "cargo-listagem";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("cargo", new Cargo());
        return "cargo-cadastro";
    }

    @PostMapping("/gravar")
    public String gravar(@ModelAttribute Cargo cargo) {
        cargoService.cadastrar(cargo);
        return "redirect:/cargos/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Cargo> cargo = cargoService.obter(id);
        model.addAttribute("cargo", cargo.orElse(new Cargo()));
        return "cargo-cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        cargoService.remover(id);
        return "redirect:/cargos/lista";
    }
}
