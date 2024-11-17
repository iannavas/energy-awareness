package com.fiap.energy_awareness.controller;

import com.fiap.energy_awareness.dto.CadastroUsuarioDTO;
import com.fiap.energy_awareness.model.Usuario;
import com.fiap.energy_awareness.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/cadastro")
    public String formularioCadastro(){
        return "register";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String cadastrarUsuario(@ModelAttribute CadastroUsuarioDTO cadastroUsuarioDTO, HttpSession session){
        try {
            Usuario usuario = usuarioService.cadastrarUsuario(cadastroUsuarioDTO);

            return "redirect:/dashboard";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }
}
