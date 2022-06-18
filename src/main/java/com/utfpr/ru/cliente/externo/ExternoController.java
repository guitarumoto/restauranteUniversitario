package com.utfpr.ru.cliente.externo;

import com.utfpr.ru.funcionario.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExternoController {

    @Autowired
    private ExternoService service;

    @PostMapping("/externo/save")
    public void saveExterno(Externo externo) {
        service.save(externo);
        //ra.addFlashAttribute("message", "O funcionario foi adicionado com sucesso");
    }

    @GetMapping("/externo/new")
    public String showNewExternoForm(Model model) {
        model.addAttribute("externo", new Externo());
        model.addAttribute("pageTitle", "Adicionar novo Cliente Externo");
        return "externo_form";
    }

    @PostMapping("/newExterno/save")
    public String saveNewExterno(Externo externo, RedirectAttributes ra) {
        service.save(externo);
        return "redirect:/pedidos/externo/new";
    }
}
