package com.codigo.ms_echarrirozas_hexagonal.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Empleado")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String tipoDoc;
    @Column(unique = true)
    private String numDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
    private Timestamp dateCrea;
    private String usuaCrea;
    private Timestamp dateUpdate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;
}
