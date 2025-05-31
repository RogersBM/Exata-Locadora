package com.locadora.exata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "alugueis")
public class Locacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotNull(message = "O cliente é obrigatório.")
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cliente_id", nullable = false)
//    private Cliente cli;

    @NotNull(message = "O cliente é obrigatório.")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cli_id", referencedColumnName = "id")
    private Cliente cli;
    
//    @NotNull(message = "O equipamento é obrigatório.")
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "equipamento_id", nullable = false)
//    private Equipamento equip;
//    
    @NotNull(message = "O equipamento é obrigatório.")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    private Equipamento equip;

    @NotNull(message = "A data de início é obrigatória.")
    @Column(nullable = false)
    private LocalDate dataInicio;

//    @NotNull(message = "O funcionário é obrigatório.")
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "funcionario_id", nullable = false)
//    private Funcionario func;
    
    @NotNull(message = "O funcionário é obrigatório.")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "func_id", referencedColumnName = "id")
    private Funcionario func;
    
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "total")
    private Double total;

    public Locacao() {
        // Inicializa objetos para evitar erro no binding do formulário
        this.cli = new Cliente();
        this.func = new Funcionario();
        this.equip = new Equipamento();
        
    }

    public Locacao(Integer id, LocalDate dataInicio, Cliente cliente, Funcionario funcionario, Equipamento equipamento,  LocalDate dataFim) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.cli = cliente;
        this.func = funcionario;
        this.equip = equipamento;
        
        
    }

   
}
