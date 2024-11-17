package com.fiap.energy_awareness.service;

import com.fiap.energy_awareness.dto.CadastroUsuarioDTO;
import com.fiap.energy_awareness.model.Configuracoes;
import com.fiap.energy_awareness.model.Usuario;
import com.fiap.energy_awareness.repository.ConfiguracoesRepository;
import com.fiap.energy_awareness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConfiguracoesRepository configuracoesRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Usuario cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) throws Exception {
        if (usuarioRepository.findByEmail(cadastroUsuarioDTO.email()) != null) {
            throw new Exception("Esse usuário já possui um cadastro");
        }

        String senhaEncode = passwordEncoder.encode(cadastroUsuarioDTO.senha());

        Configuracoes configuracoes = configuracoesRepository.save(new Configuracoes(cadastroUsuarioDTO.email()));
        return usuarioRepository.save(new Usuario(cadastroUsuarioDTO, senhaEncode, configuracoes));
    }
}
