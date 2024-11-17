package com.fiap.energy_awareness.service;

import com.fiap.energy_awareness.dto.ConfiguracoesDTO;
import com.fiap.energy_awareness.model.Configuracoes;
import com.fiap.energy_awareness.model.Usuario;
import com.fiap.energy_awareness.repository.ConfiguracoesRepository;
import com.fiap.energy_awareness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracoesService {

    @Autowired
    private ConfiguracoesRepository configuracoesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvar(Long idUsuario, ConfiguracoesDTO configuracoesDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Configuracoes configuracoes = new Configuracoes(configuracoesDTO, usuario);

        configuracoesRepository.save(configuracoes);
    }

    public void alterarConfiguracoes(Long idUsuario, ConfiguracoesDTO configuracoesDTO){
        Configuracoes configuracoes = usuarioRepository.findById(idUsuario).get().getConfiguracoes();
        configuracoes.alterar(configuracoesDTO);

        configuracoesRepository.save(configuracoes);
    }

    public ConfiguracoesDTO obterConfiguracoes(Long idUsuario) {
        Configuracoes configuracoes = usuarioRepository.findById(idUsuario).get().getConfiguracoes();

        return configuracoes == null
                ? new ConfiguracoesDTO(null, null, null)
                : new ConfiguracoesDTO(configuracoes.getLimiteConsumo(), configuracoes.getNotificacoesAtivadas(), configuracoes.getEmailNotificacoes());
    }
}
