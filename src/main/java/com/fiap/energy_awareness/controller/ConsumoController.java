package com.fiap.energy_awareness.controller;

import com.fiap.energy_awareness.dto.CadastrarConsumoDTO;
import com.fiap.energy_awareness.service.ConsumoService;
import com.fiap.energy_awareness.service.NotificacaoService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public String consumo(CadastrarConsumoDTO cadastrarConsumoDTO, Model model){
        model.addAttribute("consumos", consumoService.listar());
        return "consumer";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute CadastrarConsumoDTO cadastrarConsumoDTO, HttpSession session){
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        consumoService.cadastrar(idUsuario, cadastrarConsumoDTO);

        notificacaoService.notificar(idUsuario, consumoService.somarQuantidadeConsumo(idUsuario));
        return "redirect:/consumo";
    }


    @GetMapping("/deletar/{id}")
    @Transactional
    public String deletarConsumo(@PathVariable Long id, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("idUsuario");

        consumoService.deletarConsumo(idUsuario, id);
        return "redirect:/consumo";
    }
}
