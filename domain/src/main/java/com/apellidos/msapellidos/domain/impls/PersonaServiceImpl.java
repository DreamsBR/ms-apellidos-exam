package com.apellidos.msapellidos.domain.impls;

import com.apellidos.msapellidos.domain.aggregates.dto.PersonaDTO;
import com.apellidos.msapellidos.domain.aggregates.request.RequestPersona;
import com.apellidos.msapellidos.domain.ports.in.PersonaServiceIn;
import com.apellidos.msapellidos.domain.ports.out.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.crearPersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(Long id) {
        return personaServiceOut.obtenerPersonaOut(id);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return personaServiceOut.actualizarOut(id, requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }

    @Override
    public List<PersonaDTO> findByDniExistIn() {
        return personaServiceOut.findByDniExistOut();
    }

    @Override
    public Optional<PersonaDTO> findByDniIn(String dni) {
        return personaServiceOut.findByDniOut(dni);
    }
}
