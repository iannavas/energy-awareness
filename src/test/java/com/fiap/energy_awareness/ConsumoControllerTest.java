package com.fiap.energy_awareness;

import com.fiap.energy_awareness.controller.ConsumoController;
import com.fiap.energy_awareness.dto.CadastrarConsumoDTO;
import com.fiap.energy_awareness.service.ConsumoService;
import com.fiap.energy_awareness.service.NotificacaoService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ConsumoControllerTest {

    @InjectMocks
    private ConsumoController consumoController;

    @Mock
    private ConsumoService consumoService;

    @Mock
    private NotificacaoService notificacaoService;

    @Mock
    private HttpSession session;

    @Mock
    private RedirectAttributes redirectAttributes;

    private Long idUsuario;
    private CadastrarConsumoDTO cadastrarConsumoDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        idUsuario = 1L;
        when(session.getAttribute("idUsuario")).thenReturn(idUsuario);

        cadastrarConsumoDTO = new CadastrarConsumoDTO(LocalDate.now(), 100.0);
    }

    @Test
    public void testCadastrar() {
        doNothing().when(consumoService).cadastrar(eq(idUsuario), any(CadastrarConsumoDTO.class));
        when(consumoService.somarQuantidadeConsumo(idUsuario)).thenReturn(100.0);
        doNothing().when(notificacaoService).notificar(eq(idUsuario), eq(100.0));
        String result = consumoController.cadastrar(cadastrarConsumoDTO, session);

        verify(consumoService).cadastrar(eq(idUsuario), any(CadastrarConsumoDTO.class));
        verify(consumoService).somarQuantidadeConsumo(eq(idUsuario));
        verify(notificacaoService).notificar(eq(idUsuario), eq(100.0));

        assertEquals("redirect:/consumo", result);
    }
}
