package com.fiap.energy_awareness.service;

import com.fiap.energy_awareness.dto.CadastrarConsumoDTO;
import com.fiap.energy_awareness.model.Consumo;
import com.fiap.energy_awareness.model.Usuario;
import com.fiap.energy_awareness.repository.ConsumoRepository;
import com.fiap.energy_awareness.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrar(Long idUsuario, CadastrarConsumoDTO cadastrarConsumoDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Consumo consumo = new Consumo(cadastrarConsumoDTO, usuario);

        usuario.getConsumos().add(consumo);

        consumoRepository.save(consumo);
        usuarioRepository.save(usuario);
    }

    public List<Consumo> listar() {
        return consumoRepository.findAll();
    }

    public void deletarConsumo(Long idUsuario, Long id) {
        Consumo consumoById = consumoRepository.findById(id).get();
        Usuario usuarioyById = usuarioRepository.findById(idUsuario).get();
        usuarioyById.getConsumos().remove(consumoById);

        usuarioRepository.save(usuarioyById);
        consumoRepository.deleteById(id);
    }

    public Double somarQuantidadeConsumo(Long idUsuario) {
        List<Consumo> consumos = usuarioRepository.findById(idUsuario).get().getConsumos();

        Double quantidadeDeConsumo = 0.0;
        for(Consumo consumo : consumos){
            quantidadeDeConsumo += consumo.getQuantidadeConsumo();
        }

        return quantidadeDeConsumo;
    }
}
