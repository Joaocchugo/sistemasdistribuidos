package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Produto {
    /**
     * @Column(nullable = false) - Aceita null no back, mas não aceita no BD
     * @NotNull - Não aceita null nem no back e nem no BD
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull @NotEmpty
    private String titulo;

    /** Código de Barras */
    @NotBlank /** Pode ser vazio mas não null */
    private String ean;

    /** Outros atributos */
    @NotNull
    private String descricao;

    @NotNull @DecimalMin("0.05")
    /** @DecimalMin - Define um valor mínimo para o atributo */
    private double preco;

    @NotNull
    private int estoque;


}
