package org.example.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaResponse {

    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valorTransferencia;
    private BigDecimal valorTaxa;
    private BigDecimal valorTotalTransferencia;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;

}
