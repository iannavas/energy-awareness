package com.fiap.energy_awareness.model;

import com.fiap.energy_awareness.dto.ConfiguracoesDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Configuracoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double limiteConsumo;
    private Boolean notificacoesAtivadas;
    private String emailNotificacoes;
    @OneToOne
    private Usuario usuario;

    public Configuracoes(ConfiguracoesDTO configuracoesDTO, Usuario usuario) {
        this.limiteConsumo = configuracoesDTO.limiteConsumo();
        this.notificacoesAtivadas = configuracoesDTO.notificacoesAtivadas();
        this.emailNotificacoes = configuracoesDTO.emailNotificacoes();
        this.usuario = usuario;
    }

    public Configuracoes(String emailNotificacoes) {
        this.notificacoesAtivadas = true;
        this.emailNotificacoes = emailNotificacoes;
    }

    public void alterar(ConfiguracoesDTO configuracoesDTO) {
        if(configuracoesDTO.limiteConsumo() != null) this.limiteConsumo = configuracoesDTO.limiteConsumo();
        if(configuracoesDTO.notificacoesAtivadas() != null) this.notificacoesAtivadas = configuracoesDTO.notificacoesAtivadas();
        if(configuracoesDTO.emailNotificacoes() != null) this.emailNotificacoes = configuracoesDTO.emailNotificacoes();
    }
}
