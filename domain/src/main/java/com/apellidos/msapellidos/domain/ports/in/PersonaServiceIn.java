package com.apellidos.msapellidos.domain.ports.in;

import com.apellidos.msapellidos.domain.aggregates.dto.PersonaDTO;
import com.apellidos.msapellidos.domain.aggregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaIn(Long id);
    List<PersonaDTO> obtenerTodosIn();
    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);
    PersonaDTO deleteIn(Long id);
    List<PersonaDTO> findByDniExistIn();
    Optional<PersonaDTO> findByDniIn(String dni);
}
