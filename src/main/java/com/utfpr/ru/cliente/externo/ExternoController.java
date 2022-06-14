package com.utfpr.ru.cliente.externo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExternoController {

    @Autowired
    private ExternoService service;

    @PostMapping("/externo/save")
    public void saveExterno(Externo externo) {
        service.save(externo);
        //ra.addFlashAttribute("message", "O funcionario foi adicionado com sucesso");
    }
}
