package com.codigo.ms_echarrirozas_hexagonal.infraestructure.service.impl;

import com.codigo.ms_echarrirozas_hexagonal.infraestructure.constants.Constants;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.response.ResponseReniec;
import com.codigo.ms_echarrirozas_hexagonal.infraestructure.service.ReniecService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReniecServiceImp implements ReniecService {

    private final RestTemplate restTemplate;
    private String token = "apis-token-11190.XuZgRhBonrjpjA5JmQdGWN5mlOfUhujN";

    @Override
    public ResponseReniec getInfoReniec(String numDoc) {
        log.info("Consultando información de RENIEC para el DNI: {}", numDoc);
        //Consultamos directamente al cliente externo
        ResponseReniec responseReniec = executeRestTemplate(numDoc);
        if(responseReniec == null){
            log.warn("No se pudo obtener informacion para el DNI: {}", numDoc);
        }else {
            log.info("Información obtenida exitosamente para el DNI: {}", numDoc);
        }
        return responseReniec;
    }

    private ResponseReniec executeRestTemplate(String numDoc){
        //urlComplete = https://api.apis.net.pe/v2/sunat/ruc/full?numero=12345678912
        String urlComplete = Constants.BASE_URL + "/v2/reniec/dni?numero=" + numDoc;

        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    urlComplete,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders()),
                    ResponseReniec.class
            );

            //validarmos la repsuesta del servicio
            if(response.getStatusCode() == HttpStatus.OK){
                return response.getBody();
            } else {
                log.warn("Respuesta inesperada del servicio externo: {}", response.getStatusCode());
            }
        } catch (Exception e){
            log.error("Error al consultar el servicio externo para el DNI: {}", numDoc, e);
        }

        return  null;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", Constants.BEARER+token);
        return headers;
    }
}
