package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class TransferenciaRequest {
    @NotBlank(message = "Conta é obrigatória")
    @Size(min = 10, max = 10, message = "Conta inválida!")
    @Pattern(regexp = "^\\d{5,12}-[0-9X]$", message = "Conta fora do padrão 00000000-0")
    String contaOrigem;

    @NotBlank(message = "Conta é obrigatória")
    @Size(min = 10, max = 10, message = "Conta inválida!")
    @Pattern(regexp = "^\\d{5,12}-[0-9X]$", message = "Conta fora do padrão 00000000-0")
    String contaDestino;

    @NotNull(message = "Valor é obrigatório!")
    BigDecimal valorTransferencia;

    @NotNull(message = "Data de Transferência obrigatória!")
    LocalDate dataTransferencia;

}
