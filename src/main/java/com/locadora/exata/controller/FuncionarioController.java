
package com.locadora.exata.controller;

import com.locadora.exata.model.Funcionario;
import com.locadora.exata.service.CargoService;
import com.locadora.exata.service.FuncionarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    
    @Autowired
    private CargoService cargoService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listar());
        return "funcionario-listagem";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("cargos", cargoService.listar());
        return "funcionario-cadastro";
    }

    @PostMapping("/gravar")
    public String gravar(@ModelAttribute Funcionario funcionario) {
        funcionarioService.cadastrar(funcionario);
        return "redirect:/funcionarios/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Funcionario> funcionario = funcionarioService.obter(id);
        model.addAttribute("funcionario", funcionario.orElse(new Funcionario()));
        model.addAttribute("cargos", cargoService.listar());
        return "funcionario-cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        funcionarioService.remover(id);
        return "redirect:/funcionarios/lista";
    }
}