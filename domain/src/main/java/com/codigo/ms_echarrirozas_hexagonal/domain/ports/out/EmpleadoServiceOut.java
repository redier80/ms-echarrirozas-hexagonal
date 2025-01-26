package com.codigo.ms_echarrirozas_hexagonal.domain.ports.out;

import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.request.RequestEmpleado;

import java.util.List;

public interface EmpleadoServiceOut {
     EmpleadoDto crearEmpleadoOut(String dni, RequestEmpleado requestEmpleado);
     EmpleadoDto buscarEmpleadoOut(String dni);
     List<EmpleadoDto> mostrarTodosOut();
     EmpleadoDto actualizarEmpleadoOut(String numDoc, RequestEmpleado requestEmpleado);
     void eliminarEmpleadoIn(String dni);
}
