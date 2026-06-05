package org.example.service;

import org.example.domain.FaixaTaxa;
import org.example.exception.RegraNegocioException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TaxaService {

    List<FaixaTaxa> tabelaTaxas = List.of(
        new FaixaTaxa(0, 0,
                new BigDecimal("3.00"),
                new BigDecimal("2.5")),

        new FaixaTaxa(1, 10,
                new BigDecimal("12.00"),
                BigDecimal.ZERO),

        new FaixaTaxa(11, 20,
                BigDecimal.ZERO,
                new BigDecimal("8.2")),

        new FaixaTaxa(21, 30,
                BigDecimal.ZERO,
                new BigDecimal("6.9")),

        new FaixaTaxa(31, 40,
                BigDecimal.ZERO,
                new BigDecimal("4.7")),

        new FaixaTaxa(41, 50,
                BigDecimal.ZERO,
                new BigDecimal("1.7"))
    );


    public BigDecimal calcularTaxa(long diasParaTransferencia, @NotNull(message = "Valor é obrigatório!") BigDecimal valorTransferencia) {
        FaixaTaxa faixa = tabelaTaxas.stream()
                .filter(t -> t.atende(diasParaTransferencia))
                .findFirst().orElseThrow(() ->
                        new RegraNegocioException("Não existe taxa aplicável para o período informado"));;

        BigDecimal taxaPercentual =
                valorTransferencia.multiply(faixa.getPercentual())
                        .divide(new BigDecimal("100"));

        return faixa.getTaxaFixa().add(taxaPercentual);
    }
}
