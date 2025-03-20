package com.azvtech.bus_geo_api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "bus_stop", schema = "public")
@Data
public class BusStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    private String stopName;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point wkbGeometry; // Ponto geográfico (SRID 4326)
}
