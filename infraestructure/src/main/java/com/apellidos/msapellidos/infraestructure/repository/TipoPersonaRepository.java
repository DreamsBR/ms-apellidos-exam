package com.apellidos.msapellidos.infraestructure.repository;

import com.apellidos.msapellidos.infraestructure.entity.TipoDocumentoEntity;
import com.apellidos.msapellidos.infraestructure.entity.TipoPersonaEntity;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity, Long> {

    TipoPersonaEntity findByCodTipo(@PathParam("codTipo") String codTipo);

}
