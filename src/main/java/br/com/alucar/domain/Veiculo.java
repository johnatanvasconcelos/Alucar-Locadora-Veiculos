package br.com.alucar.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", nullable = false, unique = true, length = 10)
    private String placa;

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Column(name = "valor_diaria", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDiaria = BigDecimal.valueOf(79.90);

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    protected Veiculo(){}

    public Veiculo(String placa, String modelo, Tipo tipo, BigDecimal valorDiaria) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.valorDiaria = valorDiaria; // Atribuir o valor recebido
        this.valorDiaria = calcularValorDiaria(); // Depois calcular com desconto se aplicável
        this.ativo = true;
    }

    public BigDecimal calcularValorDiaria() {
        if (tipo == Tipo.MOTO) {
            return valorDiaria.multiply(BigDecimal.valueOf(0.8)); // 20% de desconto para motos
        }
        return valorDiaria;
    }

    public Long getId(){
        return id;
    }
    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public Tipo getTipo() {
        return tipo;
    }
    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }
    public boolean isAtivo() {
        return ativo;
    }
}
