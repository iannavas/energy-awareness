package com.fiap.energy_awareness.controller;

import com.fiap.energy_awareness.dto.ConfiguracoesDTO;
import com.fiap.energy_awareness.service.ConfiguracoesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/configuracoes")
public class ConfiguracoesController {

    @Autowired
    private ConfiguracoesService configuracoesService;

    @GetMapping
    public String configuracoes(Model model, HttpSession session, ConfiguracoesDTO configuracoesDTO){
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        ConfiguracoesDTO configuracoes = configuracoesService.obterConfiguracoes(idUsuario);
        model.addAttribute("configuracoesDTO", configuracoes);

        return "configuracoes";
    }

    @PostMapping
    public String salvarConfiguracoes(@ModelAttribute ConfiguracoesDTO configuracoesDTO, HttpSession session){
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        configuracoesService.alterarConfiguracoes(idUsuario, configuracoesDTO);

        return "redirect:/configuracoes";
    }

}
