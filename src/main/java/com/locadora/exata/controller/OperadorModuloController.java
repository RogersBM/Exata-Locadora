package com.locadora.exata.controller;


import com.locadora.exata.service.OperadorModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OperadorModuloController {

    @Autowired
    private OperadorModuloService operadorModuloService;

    @GetMapping("/operador-home")
    public String exibirOperadorModulo(Model model) {
        model.addAttribute("dados", operadorModuloService.obterResumoOperador());
        return "modulo-operador"; // Thymeleaf: templates/modulo-operador.html
    }
}
