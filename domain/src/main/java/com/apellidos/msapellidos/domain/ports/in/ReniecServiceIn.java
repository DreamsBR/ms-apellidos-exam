package com.apellidos.msapellidos.domain.ports.in;

import com.apellidos.msapellidos.domain.aggregates.response.ResponseReniec;

public interface ReniecServiceIn {
    ResponseReniec getInfoIn(String numero);

}
