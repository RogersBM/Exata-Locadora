package com.locadora.exata.controller;

import com.locadora.exata.model.Locacao;
import com.locadora.exata.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping("/locacoes")
    public String exibirFormulario(Model model) {
        model.addAttribute("relatorio", List.of());
        return "relatorio-locacoes";
    }

    @PostMapping("/locacoes")
    public String gerarRelatorio(
        @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
        @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
        Model model) {

        List<Locacao> relatorio = locacaoService.buscarPorPeriodo(dataInicio, dataFim);
        model.addAttribute("relatorio", relatorio);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        return "relatorio-locacoes";
    }
}
