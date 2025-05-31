package com.locadora.exata.controller;

import com.locadora.exata.model.NotaFiscal;
import com.locadora.exata.service.NotaFiscalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("notas", notaFiscalService.listar());
        return "nota-fiscal-listagem";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("notaFiscal", new NotaFiscal());
        return "nota-fiscal-cadastro";
    }

    @PostMapping("/gravar")
    public String gravar(@Valid @ModelAttribute NotaFiscal notaFiscal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("notaFiscal", notaFiscal);
            return "nota-fiscal-cadastro";
        }

        notaFiscalService.cadastrar(notaFiscal);
        return "redirect:/notas-fiscais/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Optional<NotaFiscal> nota = notaFiscalService.obter(id);
        model.addAttribute("notaFiscal", nota.orElse(new NotaFiscal()));
        return "nota-fiscal-cadastro";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        notaFiscalService.remover(id);
        return "redirect:/notas-fiscais/lista";
    }
}
