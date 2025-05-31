
package com.locadora.exata.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rudy Rogers
 */
@Entity
@Table (name = "categorias")
@Data
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Column(nullable = false)
    private String nome;
    
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) // Relacionamento reverso
    private List<Equipamento> equipamento = new ArrayList<>();
    
    
    public Categoria() {
    }  
        
    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria1 = (Categoria) obj;
        return id == categoria1.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
    
}
