package com.utfpr.ru.pedido;

import com.utfpr.ru.funcionario.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PedidoController {

    @Autowired private PedidoService service;

    @GetMapping("/pedidos/new")
    public String showNewForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("pageTitle", "Pedido Cliente Externo");
        return "pedido_externo_form";
    }

    @PostMapping("/pedidos/save")
    public String savePedido(Pedido pedido, RedirectAttributes ra) {
        service.cpfVerification(pedido);
        service.save(pedido);
        ra.addFlashAttribute("message", "O pedido foi adicionado com sucesso");

        return "redirect:/funcionarios";
    }

}
