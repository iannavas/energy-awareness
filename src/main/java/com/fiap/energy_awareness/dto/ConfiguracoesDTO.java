package com.fiap.energy_awareness.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ConfiguracoesDTO(
        Double limiteConsumo,
        @NotNull
        Boolean notificacoesAtivadas,
        @Email
        String emailNotificacoes
) {
}
