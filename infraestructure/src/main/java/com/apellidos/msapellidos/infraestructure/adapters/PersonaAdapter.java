package com.apellidos.msapellidos.infraestructure.adapters;

import com.apellidos.msapellidos.domain.aggregates.constans.Constans;
import com.apellidos.msapellidos.domain.aggregates.dto.PersonaDTO;
import com.apellidos.msapellidos.domain.aggregates.request.RequestPersona;
import com.apellidos.msapellidos.domain.aggregates.response.ResponseReniec;
import com.apellidos.msapellidos.domain.ports.out.PersonaServiceOut;
import com.apellidos.msapellidos.infraestructure.entity.PersonaEntity;
import com.apellidos.msapellidos.infraestructure.entity.TipoDocumentoEntity;
import com.apellidos.msapellidos.infraestructure.entity.TipoPersonaEntity;
import com.apellidos.msapellidos.infraestructure.mapper.PersonaMapper;
import com.apellidos.msapellidos.infraestructure.repository.PersonaRepository;
import com.apellidos.msapellidos.infraestructure.repository.TipoDocumentoRepository;
import com.apellidos.msapellidos.infraestructure.repository.TipoPersonaRepository;
import com.apellidos.msapellidos.infraestructure.rest.client.ClientReniec;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonaAdapter implements PersonaServiceOut {


    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final PersonaMapper personaMapper;
    private final ClientReniec reniec;

    @Value("${token_api}")
    private String tokenApi;

    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec = getExecutionReniec(requestPersona.getNumDoc());
        personaRepository.save(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(Long id) {
        return Optional.ofNullable(personaMapper.mapToDto(personaRepository.findById(id).get()));

    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findAll();
        for (PersonaEntity persona: entities){
            PersonaDTO personaDTO = personaMapper.mapToDto(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            ResponseReniec responseReniec = getExecutionReniec(requestPersona.getNumDoc());
            entity.get().setUsuaModif(Constans.USER);
            entity.get().setDateModif(getTimestamp());
            personaRepository.save(getEntityUpdate(responseReniec,entity.get()));
            return personaMapper.mapToDto(getEntityUpdate(responseReniec,entity.get()));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constans.USER);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMapper.mapToDto(entity.get());
        }
        return null;
    }

    @Override
    public List<PersonaDTO> findByDniExistOut() {

        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findPersonExist();
        for (PersonaEntity persona: entities){
            PersonaDTO personaDTO = personaMapper.mapToDto(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public Optional<PersonaDTO> findByDniOut(String dni) {
        Optional<PersonaEntity> dniPerson = personaRepository.findByNumDocu(dni);
        if(dniPerson.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(personaMapper.mapToDto(dniPerson.get()));
    }

    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer " + tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,authorization);
        return  responseReniec;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPersona());

        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constans.STATUS_ACTIVE);
        entity.setUsuaCrea(Constans.USER);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersona);
        return entity;
    }

    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setDateModif(getTimestamp());
        personaActualizar.setUsuaModif(Constans.USER);
        return personaActualizar;
    }


}
