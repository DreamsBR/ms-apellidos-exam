package com.apellidos.msapellidos.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "TipoDocumentoEntity.findByCodTipo", query = "select a from TipoDocumentoEntity a where a.codTipo=:codTipo")
@Entity
@Table(name = "tipo_documento")
@Getter
@Setter
public class TipoDocumentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento")
    private int idTipoDocumento;
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
