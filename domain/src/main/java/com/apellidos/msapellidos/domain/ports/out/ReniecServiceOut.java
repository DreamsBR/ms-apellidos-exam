package com.apellidos.msapellidos.domain.ports.out;

import com.apellidos.msapellidos.domain.aggregates.response.ResponseReniec;

public interface ReniecServiceOut {
    ResponseReniec getInfoIn(String numero);
}
