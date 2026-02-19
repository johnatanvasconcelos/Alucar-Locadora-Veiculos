package br.com.alucar.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoReal;
    private Status status;
    private BigDecimal valorBase;
    BigDecimal valorFinal;
    private LocalDateTime dataCriacao;
}
