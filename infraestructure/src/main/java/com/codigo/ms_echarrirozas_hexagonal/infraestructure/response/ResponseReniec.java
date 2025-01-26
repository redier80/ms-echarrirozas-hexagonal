package com.codigo.ms_echarrirozas_hexagonal.infraestructure.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseReniec {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;
}
