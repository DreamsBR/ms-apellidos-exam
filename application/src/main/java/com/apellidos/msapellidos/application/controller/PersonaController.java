package com.apellidos.msapellidos.application.controller;

import com.apellidos.msapellidos.domain.aggregates.dto.PersonaDTO;
import com.apellidos.msapellidos.domain.aggregates.request.RequestPersona;
import com.apellidos.msapellidos.domain.ports.in.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@OpenAPIDefinition(
        info = @Info(
                title = "API-PERSONA",
                version = "1.0",
                description = "Mantenimiento de una Persona"
        )
)

@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;

    @Operation(summary = "Crea una Persona")

    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }

    @Operation(summary = "Obtener una Persona por ID")

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO>obtenerPersona(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(id).get());

    }
    @Operation(summary = "Obtener todas las Peronas")

    @GetMapping()
    public ResponseEntity<List<PersonaDTO>>obtenerTodos(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodosIn());

    }
    @Operation(summary = "Obtener Personas x DNI")
    @GetMapping("/bydni/{dni}")
    public ResponseEntity<Optional<PersonaDTO>> getByDni(@PathVariable("dni") String dni){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.findByDniIn(dni));

    }
    @Operation(summary = "Actualizar Personas x DNI")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO>actualizar(@PathVariable Long id,@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarIn(id,requestPersona));

    }

    @Operation(summary = "Eliminar Personas x DNI")
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDTO>deleteLog(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.deleteIn(id));

    }
    @Operation(summary = "Obtener solo existes")

    @GetMapping("/exist")
    public ResponseEntity<List<PersonaDTO>>deleteLog(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.findByDniExistIn());

    }

}
