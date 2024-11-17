package com.fiap.energy_awareness.service;

import com.fiap.energy_awareness.config.RabbitMqConfig;
import com.fiap.energy_awareness.model.Configuracoes;
import com.fiap.energy_awareness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    public void notificar(Long idUsuario, Double quantidadeConsumo){
        Configuracoes configuracoes = usuarioRepository.findById(idUsuario).get().getConfiguracoes();

        if(!configuracoes.getNotificacoesAtivadas()) return;

        if(quantidadeConsumo > configuracoes.getLimiteConsumo()){
            System.out.println("Limite excedido, notificando usuário");
            rabbitMqConfig.send(configuracoes.getEmailNotificacoes());
        }
    }
}
