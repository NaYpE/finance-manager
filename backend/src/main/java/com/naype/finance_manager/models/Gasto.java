package com.naype.finance_manager.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id")  // Relación con Tarjeta
    private Tarjeta tarjeta;

    @Column(nullable = true)
    private  String descripcion;

    @Column(nullable = false)
    private String categoria;  // Ejemplo: "Comida", "Hogar", "Seguros"

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDate fecha;
}
