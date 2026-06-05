package org.example.controller;

import org.example.dto.TransferenciaRequest;
import org.example.dto.TransferenciaResponse;
import org.example.service.TransferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/transferencia")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public List<TransferenciaResponse> exibirTransferencias() {
        return transferenciaService.listarTransferencias();
    }

    @PostMapping
    public ResponseEntity<TransferenciaResponse> agendar(@RequestBody @Valid TransferenciaRequest dadosTransferencia) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transferenciaService.cadastrarAgendamento(dadosTransferencia));
    }
}