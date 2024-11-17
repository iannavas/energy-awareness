package com.fiap.energy_awareness.model;

import com.fiap.energy_awareness.dto.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    @OneToOne
    private Configuracoes configuracoes;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consumo> consumos;

    public Usuario(CadastroUsuarioDTO cadastroUsuarioDTO, String senhaEncode, Configuracoes configuracoes) {
        this.nome = cadastroUsuarioDTO.nome();
        this.sobrenome = cadastroUsuarioDTO.sobrenome();
        this.email = cadastroUsuarioDTO.email();
        this.senha = senhaEncode;
        this.configuracoes = configuracoes;
    }
}
