package com.locadora.exata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "notasfiscais")
@Data
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O número da nota fiscal é obrigatório.")
    @Column(nullable = false)
    private String numero;

    @NotNull(message = "O valor da nota fiscal é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor da nota deve ser maior que zero.")
    private Double valor;

    @NotNull(message = "A data de emissão é obrigatória.")
    private LocalDate dataEmissao;

    public NotaFiscal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal that = (NotaFiscal) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return numero;
    }
}
