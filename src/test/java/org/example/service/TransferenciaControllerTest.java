package org.example.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.TransferenciaController;
import org.example.dto.TransferenciaRequest;
import org.example.dto.TransferenciaResponse;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(TransferenciaController.class)
class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransferenciaService transferenciaService;

    @Test
    void deveListarTransferencias() throws Exception {

        List<TransferenciaResponse> transferencias = List.of(
                TransferenciaResponse.builder()
                        .id(1L)
                        .contaOrigem("12345678-9")
                        .contaDestino("23456789-0")
                        .valorTransferencia(BigDecimal.valueOf(100))
                        .valorTaxa(BigDecimal.valueOf(3))
                        .valorTotalTransferencia(BigDecimal.valueOf(103))
                        .dataAgendamento(LocalDate.now())
                        .dataTransferencia(LocalDate.now().plusDays(5))
                        .build()
        );

        when(transferenciaService.listarTransferencias())
                .thenReturn(transferencias);

        mockMvc.perform(get("/v1/transferencia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].contaOrigem").value("12345678-9"))
                .andExpect(jsonPath("$[0].contaDestino").value("23456789-0"));
    }

    @Test
    void deveAgendarTransferencia() throws Exception {

        TransferenciaRequest request = new TransferenciaRequest();
        request.setContaOrigem("12345678-9");
        request.setContaDestino("23456789-0");
        request.setValorTransferencia(BigDecimal.valueOf(100));
        request.setDataTransferencia(LocalDate.now().plusDays(5));

        mockMvc.perform(post("/v1/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(transferenciaService)
                .cadastrarAgendamento(any(TransferenciaRequest.class));
    }
}