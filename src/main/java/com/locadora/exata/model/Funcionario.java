package com.locadora.exata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "funcionarios")
@Data
@Component
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;
    private String identidade;
    private String contato;
    private String endereco;

    @ManyToOne
    private Cargo cargo;

    public Funcionario() {
    }

    @Override
    public String toString() {
        return nome;
    }
}
