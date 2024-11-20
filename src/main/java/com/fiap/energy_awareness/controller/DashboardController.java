package com.fiap.energy_awareness.controller;

import com.fiap.energy_awareness.dto.ConfiguracoesDTO;
import com.fiap.energy_awareness.service.ConfiguracoesService;
import com.fiap.energy_awareness.service.ConsumoService;
import com.fiap.energy_awareness.service.OllamaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    private ConfiguracoesService configuracoesService;

    @Autowired
    private OllamaService ollamaService;

    @GetMapping
    public String dashboard(HttpSession session, Model model) throws Exception {
        Long idUsuario = (Long) session.getAttribute("idUsuario");
        Double quantidadeConsumo = consumoService.somarQuantidadeConsumo(idUsuario);

        model.addAttribute("quantidadeConsumo", quantidadeConsumo);
        model.addAttribute("valorEstimado", "R$ " + quantidadeConsumo * 0.656);

        ConfiguracoesDTO configuracoesDTO = configuracoesService.obterConfiguracoes(idUsuario);
        model.addAttribute("limite", configuracoesDTO.limiteConsumo());

        return "dashboard";
    }


    @GetMapping("/recomendacoes")
    @ResponseBody
    public String carregarRecomendacao(HttpSession session){
        try {
            String mensagemEmCache = (String) session.getAttribute("recomendacaoIA");

            if(mensagemEmCache != null){
                return mensagemEmCache;
            }

            Long idUsuario = (Long) session.getAttribute("idUsuario");
            Double quantidadeConsumo = consumoService.somarQuantidadeConsumo(idUsuario);

            String recomendacao = ollamaService.gerarRecomendacao(quantidadeConsumo);
            return recomendacao;
        } catch (Exception e) {
            return "Desculpe, não foi possível gerar uma recomendação neste momento. Por favor, tente novamente mais tarde.";
        }
    }

}
