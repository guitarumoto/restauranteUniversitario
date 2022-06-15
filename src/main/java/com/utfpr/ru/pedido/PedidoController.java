package com.utfpr.ru.pedido;

import com.utfpr.ru.cliente.externo.Externo;
import com.utfpr.ru.cliente.externo.ExternoController;
import com.utfpr.ru.cliente.externo.ExternoService;
import com.utfpr.ru.funcionario.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired private PedidoService service;
    @Autowired private ExternoService serviceExterno;
    @Autowired private ExternoController controllerExterno;

    @GetMapping("/pedidos/externo/new")
    public String showNewForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("pageTitle", "Pedido Cliente Externo");
        return "pedido_externo_form";
    }

    @PostMapping("/pedidos/externo/save")
    public String savePedidoExterno(Pedido pedido, RedirectAttributes ra) {
        Pedido pedidoExterno = service.cpfVerification(pedido);
        pedido.setClienteId(pedidoExterno.getClienteId());
        if(pedidoExterno.getClienteId() != null){
            pedido.setClienteId(pedidoExterno.getClienteId());
            pedido.setDataPedido(java.time.LocalDateTime.now());
            service.save(pedido);
        }
        else{
            Externo externo = new Externo();
            externo.setEmail(pedido.getEmailCliente());
            externo.setNome(pedido.getNomeCliente());
            externo.setCpf(pedido.getExternoCpf());
            externo.setRg(pedido.getExternoRg());
            controllerExterno.saveExterno(externo);
            pedido.setDataPedido(java.time.LocalDateTime.now());
            pedido.setClienteId(externo.getId());
            service.save(pedido);
        }
        ra.addFlashAttribute("message", "O pedido foi adicionado com sucesso");

        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos")
    public String showPedidoList(Model model){
        List<Pedido> listPedidos = service.listAllPedidos();
        model.addAttribute("listPedidos", listPedidos);
        return "pedidos";
    }

}
