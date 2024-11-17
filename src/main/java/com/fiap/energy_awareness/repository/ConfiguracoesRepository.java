package com.fiap.energy_awareness.repository;

import com.fiap.energy_awareness.model.Configuracoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracoesRepository extends JpaRepository<Configuracoes, Long> {
}
