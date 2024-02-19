package com.apellidos.msapellidos.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "TipoPersonaEntity.findByCodTipo", query = "select a from TipoPersonaEntity a where a.codTipo=:codTipo")
@Entity
@Table(name = "tipo_persona")
@Getter
@Setter
public class TipoPersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoPersona;
    private String codTipo;
    private String descTipo;
    private int estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;

}
