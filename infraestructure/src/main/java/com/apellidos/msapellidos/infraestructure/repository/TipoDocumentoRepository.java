package com.apellidos.msapellidos.infraestructure.repository;

import com.apellidos.msapellidos.infraestructure.entity.PersonaEntity;
import com.apellidos.msapellidos.infraestructure.entity.TipoDocumentoEntity;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {

    TipoDocumentoEntity findByCodTipo(@PathParam("codTipo") String codTipo);

}
