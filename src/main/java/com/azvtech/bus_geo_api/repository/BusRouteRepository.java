package com.azvtech.bus_geo_api.repository;

import com.azvtech.bus_geo_api.model.BusRoute;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
    // Busca rotas que intersectam uma área (polígono)
    @Query("SELECT r FROM BusRoute r WHERE ST_Within(r.wkbGeometry, :area) = true")
    List<BusRoute> findRoutesWithinArea(@Param("area") Geometry area);

}
