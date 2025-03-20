package com.azvtech.bus_geo_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "bus_routes", schema = "public")
@Data
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    private String consorcio;
    private String descricaoDesvio;
    private String tipoRota;
    private String direcao;
    private String destino;
    private String servico;
    private Double extensao;

    @Column(columnDefinition = "Geometry")
    private Geometry wkbGeometry; // Geometria PostGIS
}
