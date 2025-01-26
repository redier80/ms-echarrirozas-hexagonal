package com.codigo.ms_echarrirozas_hexagonal.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ResponseBase<T> {
    private int codigo;
    private String mensaje;
    private Optional<T> objeto;

    public ResponseBase(int codigo, String mensaje, T objeto){
        this.codigo= codigo;
        this.mensaje = mensaje;
        this.objeto = Optional.ofNullable(objeto);;
    }

}
