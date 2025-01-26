package com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmpleado {
    private int edad;
    private String cargo;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private String direccion;
}
