package com.fiap.energy_awareness.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarConsumoDTO(
        @NotNull
        LocalDate dataConsumo,
        @NotNull
        Double quantidadeConsumo
) {
}
