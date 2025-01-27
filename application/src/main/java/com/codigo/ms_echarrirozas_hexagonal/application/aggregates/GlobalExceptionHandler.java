package com.codigo.ms_echarrirozas_hexagonal.application.aggregates;

import com.codigo.ms_echarrirozas_hexagonal.application.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<String>> handleException(Exception ex){
        ResponseBase<String> responseBase = new ResponseBase<>(500, "Error interno en el servidor",ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBase);
    }

    //manejo del nullPointerException (HTTP 409)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase<String>> handleNullPointerException(NullPointerException ex){
        ResponseBase<String> responseBase = new ResponseBase<>(409,"Recurso no encontrado", null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBase);
    }
    // Manejo de IOException (HTTP 406)
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseBase<String>> handleIOException(IOException ex) {
        ResponseBase<String> responseBase = new ResponseBase<>(406, "Error en la entrada/salida", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseBase);
    }

    // Manejo de RuntimeException (HTTP 400)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBase<String>> handleRuntimeException(RuntimeException ex) {
        ResponseBase<String> responseBase = new ResponseBase<>(400, "Solicitud incorrecta", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
    }
}
