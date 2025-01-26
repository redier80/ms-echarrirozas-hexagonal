package com.codigo.ms_echarrirozas_hexagonal.domain.ports.in;

import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.request.RequestEmpleado;

import java.util.List;

public interface EmpleadoServiceIn {
     EmpleadoDto crearEmpleadoIn(String dni, RequestEmpleado requestEmpleado);
    EmpleadoDto buscarEmpleadoIn(String dni);
    List<EmpleadoDto> mostrarTodosIn();
    EmpleadoDto actualizarEmpleadoIn(String numDoc, RequestEmpleado requestEmpleado);
    void eliminarEmpleadoIn(String dni);

}
