package org.example.controller;


import org.example.dto.TransferenciaRequest;
import org.example.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/transferencia")
public class TransferenciaController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String ExibirTransferencias(){
        return "Transferencias: Acompanhar...";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TransferenciaRequest cadastrarTransferencia(@RequestBody @Valid TransferenciaRequest dadosTransferencia ) throws RegraNegocioException
    {
        return dadosTransferencia;
    }
}
