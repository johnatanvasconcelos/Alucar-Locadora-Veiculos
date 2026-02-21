package br.com.alucar.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Column(name = "data_hora_retirada", nullable = false)
    private LocalDateTime dataHoraRetirada;

    @Column(name = "data_hora_devolucao_prevista", nullable = false)
    private LocalDateTime dataHoraDevolucaoPrevista;

    @Column(name = "data_hora_devolucao_real")
    private LocalDateTime dataHoraDevolucaoReal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "valor_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorBase;

    @Column(name = "valor_final", precision = 10, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    protected Reserva(){}

    public Reserva(Cliente cliente, Veiculo veiculo, LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucaoPrevista) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataHoraRetirada = dataHoraRetirada;
        this.dataHoraDevolucaoPrevista = dataHoraDevolucaoPrevista;
        this.status = Status.CRIADA;
        this.valorBase = calcularValorBase();
        this.dataCriacao = LocalDateTime.now();
    }

    public BigDecimal calcularValorBase() {
        long dias = java.time.Duration.between(dataHoraRetirada, dataHoraDevolucaoPrevista).toDays() + 1;
        return veiculo.getValorDiaria().multiply(BigDecimal.valueOf(dias));
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataHoraRetirada() {
        return dataHoraRetirada;
    }

    public LocalDateTime getDataHoraDevolucaoPrevista() {
        return dataHoraDevolucaoPrevista;
    }

    public LocalDateTime getDataHoraDevolucaoReal() {
        return dataHoraDevolucaoReal;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
