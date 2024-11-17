package com.fiap.energy_awareness.model;

import com.fiap.energy_awareness.dto.CadastrarConsumoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Table
@Entity
@Data
@NoArgsConstructor
public class Consumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataConsumo;
    private Double quantidadeConsumo;
    @ManyToOne
    @ToString.Exclude
    private Usuario usuario;

    public Consumo(CadastrarConsumoDTO cadastrarConsumoDTO, Usuario usuario) {
        this.dataConsumo = cadastrarConsumoDTO.dataConsumo();
        this.quantidadeConsumo = cadastrarConsumoDTO.quantidadeConsumo();
        this.usuario = usuario;
    }
}
