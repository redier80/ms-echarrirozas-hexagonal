package com.codigo.ms_echarrirozas_hexagonal.infraestructure.service;

import com.codigo.ms_echarrirozas_hexagonal.infraestructure.response.ResponseReniec;

public interface ReniecService {
    ResponseReniec getInfoReniec(String numDoc);
}
