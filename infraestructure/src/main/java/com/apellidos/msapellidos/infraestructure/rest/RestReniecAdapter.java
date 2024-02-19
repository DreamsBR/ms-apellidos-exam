package com.apellidos.msapellidos.infraestructure.rest;

import com.apellidos.msapellidos.domain.aggregates.response.ResponseReniec;
import com.apellidos.msapellidos.domain.ports.out.ReniecServiceOut;
import com.apellidos.msapellidos.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class RestReniecAdapter implements ReniecServiceOut {

    private final ClientReniec clientReniec;

    @Value("${token_api}")
    private String token_api;

    @Override
    public ResponseReniec getInfoIn(String numero) {
        String authorization = "Bearer " + token_api;
        ResponseReniec responseReniec = clientReniec.getInfoReniec(numero, authorization);
        return responseReniec;
    }
}
