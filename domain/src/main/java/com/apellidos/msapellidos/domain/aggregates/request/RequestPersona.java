package com.apellidos.msapellidos.domain.aggregates.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestPersona {

    private String tipoDoc;
    private String numDoc;
    private String tipoPersona;
}
