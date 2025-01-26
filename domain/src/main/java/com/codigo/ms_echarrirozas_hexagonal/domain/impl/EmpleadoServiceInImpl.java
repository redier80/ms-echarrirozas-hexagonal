package com.codigo.ms_echarrirozas_hexagonal.domain.impl;

import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.constants.Constants;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_echarrirozas_hexagonal.domain.ports.in.EmpleadoServiceIn;
import com.codigo.ms_echarrirozas_hexagonal.domain.ports.out.EmpleadoServiceOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpleadoServiceInImpl implements EmpleadoServiceIn {

    private final EmpleadoServiceOut empleadoServiceOut;
    private String serviceName="EmpleadoServiceInImpl";

    @Override
    public  EmpleadoDto crearEmpleadoIn(String dni, RequestEmpleado requestEmpleado) {
        String nameMetodo= "crearEmpleadoIn";
        log.info(Constants.LOG_INICIO+ serviceName,nameMetodo);
        if (Objects.nonNull(dni)){
            log.info(Constants.LOG_FINAL+serviceName,nameMetodo);
            return empleadoServiceOut.crearEmpleadoOut(dni, requestEmpleado);
        }
        log.info(Constants.LOG_ERROR+serviceName,nameMetodo);
        throw new RuntimeException("Error en creacion de persona in, el Dni es nulo");

    }

    @Override
    public EmpleadoDto buscarEmpleadoIn(String dni) {

        return empleadoServiceOut.buscarEmpleadoOut(dni);
    }

    @Override
    public List<EmpleadoDto> mostrarTodosIn() {

        return empleadoServiceOut.mostrarTodosOut();
    }

    @Override
    public EmpleadoDto actualizarEmpleadoIn(String numDoc, RequestEmpleado requestEmpleado) {
        return empleadoServiceOut.actualizarEmpleadoOut(numDoc, requestEmpleado);
    }

    @Override
    public void eliminarEmpleadoIn(String dni) {

    }
}
