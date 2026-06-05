package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter

public class FaixaTaxa {
    private final long diasInicio;
    private final long diasFim;
    private final BigDecimal taxaFixa;
    private final BigDecimal percentual;

    public boolean atende(long dias) {
        return dias >= diasInicio && dias <= diasFim;
    }
}
