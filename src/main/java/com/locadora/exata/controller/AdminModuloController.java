package com.locadora.exata.controller;

import com.locadora.exata.service.AdminModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminModuloController {

    @Autowired
    private AdminModuloService adminModuloService;

    @GetMapping("/admin-home")
    public String exibirAdminModulo(Model model) {
        // Exemplo de uso do service
        model.addAttribute("dados", adminModuloService.obterResumoAdmin());
        return "modulo-administrador"; // Thymeleaf: templates/modulo-administrador.html
    }
}
