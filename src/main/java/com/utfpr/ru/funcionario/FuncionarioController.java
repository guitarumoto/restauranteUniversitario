package com.utfpr.ru.funcionario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FuncionarioController {
    @Autowired private FuncionarioService service;

    @GetMapping("/funcionarios/login")
    public String loginFuncionario(Funcionario funcionario, RedirectAttributes ra){
        boolean log = service.loginVerification(funcionario);
        if(log){
            ra.addFlashAttribute("message", "Funcionário logado!");

            return "redirect:/funcionarios";
        }
        return "login";
    }


    @GetMapping("/funcionarios")
    public String showFuncionarioList(Model model){
        List<Funcionario> listFuncionarios = service.listAll();
        model.addAttribute("listFuncionarios", listFuncionarios);
        return "funcionarios";
    }

    @GetMapping("/funcionarios/new")
    public String showNewForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("pageTitle", "Adicionar novo usuário");
        return "funcionario_form";
    }

    @PostMapping("/funcionarios/save")
    public String saveFuncionario(Funcionario funcionario, RedirectAttributes ra) {
        service.save(funcionario);
        ra.addFlashAttribute("message", "O funcionario foi adicionado com sucesso");

        return "redirect:/funcionarios";
    }

    @GetMapping("/funcionarios/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Funcionario funcionario = service.get(id);
            model.addAttribute("funcionario", funcionario);
            model.addAttribute("pageTitle", "Editar Funcionario (ID: " + id + ")");
            return "funcionario_form";
        } catch (FuncionarioNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }

    }

    @GetMapping("/funcionarios/delete/{id}")
    public String deleteFuncionario(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "O funcionário de id " + id +" foi deletado");
        } catch (FuncionarioNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/funcionarios";
    }


}
