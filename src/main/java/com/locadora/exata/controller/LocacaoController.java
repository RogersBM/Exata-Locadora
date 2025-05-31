package com.locadora.exata.controller;

import com.locadora.exata.model.*;
import com.locadora.exata.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/alugueis")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private NotaFiscalService notaFiscalService;

    // ✅ FORMULÁRIO DE CADASTRO
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        Locacao locacao = new Locacao();
        locacao.setCli(new Cliente());
        locacao.setFunc(new Funcionario());
        locacao.setEquip(new Equipamento());

        model.addAttribute("locacao", locacao);
        model.addAttribute("clientes", safeList(clienteService.listar()));
        model.addAttribute("funcionarios", safeList(funcionarioService.listar()));
        model.addAttribute("equipamentos", safeList(equipamentoService.listar()));
        model.addAttribute("notas", safeList(notaFiscalService.listar()));

        return "locacao-cadastro";
    }

    // ✅ SALVAR NOVA LOCAÇÃO
    @PostMapping("/gravar")
    public String gravar(@Valid @ModelAttribute Locacao locacao,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("funcionarios", funcionarioService.listar());
            model.addAttribute("equipamentos", equipamentoService.listar());
            model.addAttribute("notas", notaFiscalService.listar());
            return "locacao-cadastro";
        }

        if (locacao.getCli() == null || locacao.getCli().getId() == null
                || locacao.getFunc() == null || locacao.getFunc().getId() == null
                || locacao.getEquip() == null || locacao.getEquip().getId() == null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Campos obrigatórios não selecionados.");
            return "redirect:/alugueis/cadastro";
        }

        locacaoService.criarLocacao(locacao);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Aluguel cadastrado com sucesso!");
        return "redirect:/alugueis/lista";
    }

    // ✅ LISTAGEM DE TODOS OS ALUGUÉIS
    @GetMapping("/lista")
    public String listarAlugueis(Model model) {
        model.addAttribute("locacoes", locacaoService.listarTodas());
        return "locacao-listagem";
    }

    // ✅ EDIÇÃO
    @GetMapping("/editar/{id}")
    public String editarAluguel(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Locacao locacao = locacaoService.buscarPorId(id);
            model.addAttribute("locacao", locacao);
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("funcionarios", funcionarioService.listar());
            model.addAttribute("equipamentos", equipamentoService.listar());
            return "locacao-cadastro";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Aluguel não encontrado.");
            return "redirect:/alugueis/lista";
        }
    }

    // ✅ EXCLUSÃO
    @GetMapping("/excluir/{id}")
    public String excluirAluguel(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            locacaoService.excluirLocacao(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Aluguel excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/alugueis/lista";
    }

    // ✅ TELA DE SELEÇÃO PARA ENCERRAMENTO
    @GetMapping("/encerrar")
    public String selecionarParaEncerramento(Model model,
            @ModelAttribute("mensagemSucesso") String sucesso,
            @ModelAttribute("mensagemErro") String erro,
            @ModelAttribute("mensagemInfo") String info) {
        model.addAttribute("locacoes", locacaoService.listarAbertas());
        model.addAttribute("mensagemSucesso", sucesso);
        model.addAttribute("mensagemErro", erro);
        model.addAttribute("mensagemInfo", info);
        return "locacao-encerrar";
    }

    // ✅ FORMULÁRIO DE ENCERRAMENTO
    @GetMapping("/encerrar-form/{id}")
    public String exibirFormularioEncerramento(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Locacao locacao = locacaoService.buscarPorId(id);

        if (locacao.getDataFim() != null) {
            redirectAttributes.addFlashAttribute("mensagemInfo", "Este aluguel já está encerrado.");
            return "redirect:/alugueis/encerrar";
        }

        model.addAttribute("locacao", locacao);
        return "locacao-encerrar-form";
    }

    @PostMapping("/encerrar")
    public String encerrarLocacao(@ModelAttribute Locacao locacaoForm, Model model) {
        try {
            Locacao encerrada = locacaoService.encerrarLocacao(locacaoForm.getId(), locacaoForm.getDataFim());
            model.addAttribute("mensagemSucesso", "Aluguel encerrado com sucesso!");
            model.addAttribute("locacoes", locacaoService.listarTodas()); // 🔁 inclui todos
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao encerrar: " + e.getMessage());
            model.addAttribute("locacoes", locacaoService.listarTodas()); // para exibir mesmo em erro
        }
        return "locacao-listagem"; // Não redireciona. Apenas renderiza a tela de listagem
    }

    // ✅ MÉTODO AUXILIAR PARA EVITAR ERRO DE LISTA NULA
    private <T> List<T> safeList(List<T> list) {
        return (list != null) ? list : List.of();
    }
}
