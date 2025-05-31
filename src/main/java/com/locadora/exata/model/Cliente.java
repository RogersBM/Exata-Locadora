package com.locadora.exata.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "clientes")
@Data
@Component
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String identidade;
    private String contato;
    private String endereco;
    private String email;

//    @OneToOne( orphanRemoval = true, cascade = CascadeType.PERSIST )
//    @PrimaryKeyJoinColumn
//    private Locacao loc = new Locacao();
    
        
}
