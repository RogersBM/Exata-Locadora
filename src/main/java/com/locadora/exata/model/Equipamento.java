package com.locadora.exata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "equipamentos")
@Data
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(length = 1, nullable = false)
    private String alugado;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero.")
    @NotNull(message = "O valor é obrigatório.")
    @Column(nullable = false)
    private Double valor;

    public Equipamento() {
    }

    @Override
    public String toString() {
        return nome;
    }
}
