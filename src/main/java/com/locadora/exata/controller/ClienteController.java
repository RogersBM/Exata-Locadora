package com.locadora.exata.controller;

import com.locadora.exata.model.Cliente;
import com.locadora.exata.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        return "cliente-listagem";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-cadastro";
    }

    @PostMapping("/gravar")
    public String gravar(@ModelAttribute Cliente cliente) {
        clienteService.cadastrar(cliente);
        return "redirect:/clientes/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<Cliente> cliente = clienteService.obter(id);
        model.addAttribute("cliente", cliente.orElse(new Cliente()));
        return "cliente-cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        clienteService.remover(id);
        return "redirect:/clientes/lista";
    }
}
