package com.codigo.ms_echarrirozas_hexagonal.infraestructure.mapper;

import com.codigo.ms_echarrirozas_hexagonal.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.entity.EmpleadoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){
        return MODEL_MAPPER.map(empleadoEntity, EmpleadoDto.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){
        return MODEL_MAPPER.map(empleadoDto, EmpleadoEntity.class);
    }
}
