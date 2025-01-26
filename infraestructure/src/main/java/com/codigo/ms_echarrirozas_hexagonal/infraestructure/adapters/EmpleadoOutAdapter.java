package com.codigo.ms_echarrirozas_hexagonal.infraestructure.adapters;

import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_echarrirozas_hexagonal.domain.ports.out.EmpleadoServiceOut;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.constants.Constants;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.entity.EmpleadoEntity;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.mapper.EmpleadoMapper;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.repository.EmpleadoRepository;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.response.ResponseReniec;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.service.ReniecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpleadoOutAdapter implements EmpleadoServiceOut {

    private final EmpleadoRepository empleadoRepository;
    private final ReniecService reniecService;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public  EmpleadoDto crearEmpleadoOut(String dni, RequestEmpleado requestEmpleado) {

        EmpleadoEntity empleadoEntity = getEntity(dni, requestEmpleado);

        if (empleadoEntity.getNumDoc().isEmpty()) {
            throw new RuntimeException("Error al registrar al empleado");
        }

        return empleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));

    }

    @Override
    public EmpleadoDto buscarEmpleadoOut(String numDoc) {

        EmpleadoEntity empleadoEntity = empleadoRepository.findByNumDoc(numDoc)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con numDoc: " + numDoc));

        return empleadoMapper.mapToDto(empleadoEntity);
    }

    @Override
    public List<EmpleadoDto> mostrarTodosOut() {
        return empleadoRepository.findAllByEstado(Constants.ESTADO_ACTIVO)
                .stream()
                .map(empleadoMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public EmpleadoDto actualizarEmpleadoOut(String numDoc, RequestEmpleado requestEmpleado) {
        /*Optional<EmpleadoEntity> empleadoEntityOptional = empleadoRepository.findByNumDoc(numDoc);
        if (empleadoEntityOptional.isEmpty()) {
            throw new RuntimeException("Empleado no encontrado con numDoc: " + numDoc);
        }
        EmpleadoEntity empleadoEntity = empleadoEntityOptional.get();
        empleadoEntity.setEdad(requestEmpleado.getEdad());
        empleadoEntity.setCargo(requestEmpleado.getCargo());
        empleadoEntity.setCorreo(requestEmpleado.getCorreo());
        empleadoEntity.setTelefono(requestEmpleado.getTelefono());
        empleadoEntity.setDepartamento(requestEmpleado.getDepartamento());
        empleadoEntity.setDireccion(requestEmpleado.getDireccion());

        EmpleadoEntity updatedEmpleado = empleadoRepository.save(empleadoEntity);
        return empleadoMapper.mapToDto(updatedEmpleado);*/

        empleadoRepository.actualizarEmpleado(numDoc,
                                                requestEmpleado.getEdad(),
                                                requestEmpleado.getCargo(),
                                                requestEmpleado.getSalario(),
                                                requestEmpleado.getTelefono(),
                                                requestEmpleado.getCorreo(),
                                                requestEmpleado.getDepartamento(),
                                                requestEmpleado.getDireccion());

        EmpleadoEntity updatedEmpleado = empleadoRepository.findByNumDoc(numDoc)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado después de la actualización"));

        // Convertir la entidad actualizada a DTO
        return empleadoMapper.mapToDto(updatedEmpleado);

    }

    @Override
    public void eliminarEmpleadoIn(String dni) {

    }

    private EmpleadoEntity getEntity(String dni, RequestEmpleado requestEmpleado){

        ResponseReniec responseReniec = execute(dni);

        if (Objects.nonNull(responseReniec)) {
            return EmpleadoEntity.builder()
                    .numDoc(responseReniec.getNumeroDocumento())
                    .tipoDoc(responseReniec.getTipoDocumento())
                    .apellido(String.format("%s %s", responseReniec.getApellidoPaterno(), responseReniec.getApellidoMaterno()))
                    .nombre(responseReniec.getNombres())
                    .edad(requestEmpleado.getEdad())
                    .salario(requestEmpleado.getSalario())
                    .cargo(requestEmpleado.getCargo())
                    .correo(requestEmpleado.getCorreo())
                    .departamento(requestEmpleado.getDepartamento())
                    .direccion(requestEmpleado.getDireccion())
                    .telefono(requestEmpleado.getTelefono())
                    .dateCrea(new Timestamp(System.currentTimeMillis()))
                    .usuaCrea(Constants.USUARIO)
                    .estado(Constants.ESTADO_ACTIVO)
                    .build();
        }

        // Devuelve una entidad vacía si no se encuentra información en RENIEC
        return EmpleadoEntity.builder().build();
    }

    private ResponseReniec execute(String dni){
        return reniecService.getInfoReniec(dni);
    }
}
