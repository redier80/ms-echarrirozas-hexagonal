package com.codigo.ms_echarrirozas_hexagonal.application.controller;

import com.codigo.ms_echarrirozas_hexagonal.application.response.ResponseBase;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_echarrirozas_hexagonal.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/hexagonal")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;
    @PostMapping("/{dni}")
    public ResponseEntity<ResponseBase<EmpleadoDto>> crearEmpleado(@PathVariable("dni") String dni,
                                                                  @RequestBody RequestEmpleado requestEmpleado){

        /*
        try {
            EmpleadoDto empleadoGuardado = empleadoServiceIn.crearEmpleadoIn(dni, requestEmpleado);
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(200, "Empleado registrado con éxito", empleadoGuardado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseBase<EmpleadoDto> errorResponse = new ResponseBase<>(500, "Error al guardar el empleado", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
        */
        EmpleadoDto empleadoGuardado = empleadoServiceIn.crearEmpleadoIn(dni, requestEmpleado);
        ResponseBase<EmpleadoDto> response = new ResponseBase<>(200, "Empleado registrado con éxito", empleadoGuardado);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/buscar/{numDoc}")
    public ResponseEntity<ResponseBase<EmpleadoDto>> buscarEmpleado(@PathVariable("numDoc") String numDoc){

        EmpleadoDto empleadoDto = empleadoServiceIn.buscarEmpleadoIn(numDoc);
        ResponseBase<EmpleadoDto> responseBase = new ResponseBase<>(200, "Empleado Encontrado satisfactoriamente", empleadoDto);
        return ResponseEntity.ok(responseBase);
    }
    @GetMapping("/mostrarTodos")
    public ResponseEntity<ResponseBase<List<EmpleadoDto>>> mostrarTodos(){
        List<EmpleadoDto> empleadoDtoList = empleadoServiceIn.mostrarTodosIn();
        ResponseBase<List<EmpleadoDto>> response = new ResponseBase<>();
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje("Lista de empleados activos obtenida con éxito");
        response.setObjeto(Optional.of(empleadoDtoList));
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{numDoc}/actualizar")
    public ResponseEntity<ResponseBase<EmpleadoDto>> actualizarEmpleado(@PathVariable("numDoc") String numDoc,
                                                                        @RequestBody RequestEmpleado requestEmpleado){
        EmpleadoDto empleadoActualizar =   empleadoServiceIn.actualizarEmpleadoIn(numDoc, requestEmpleado);
        ResponseBase<EmpleadoDto> response = new ResponseBase<>();
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje("Empleado actualizado correctamente");
        response.setObjeto(Optional.of(empleadoActualizar));

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/eliminar/{numDoc}")
    public ResponseEntity<ResponseBase<Void>>eliminarEmpleado(@PathVariable("numDoc") String numDoc){
        empleadoServiceIn.eliminarEmpleadoIn(numDoc);
        ResponseBase<Void> response = new ResponseBase<>();
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje("Empleado eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}
