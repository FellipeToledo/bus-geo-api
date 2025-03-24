package com.azvtech.bus_geo_api.repository;

import com.azvtech.bus_geo_api.model.BusStop;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
    // Busca paradas dentro de um raio (metros)
    @Query("SELECT s FROM BusStop s WHERE ST_Distance(s.wkbGeometry, :point) <= :radius")
    List<BusStop> findNearbyStops(@Param("point") Point point, @Param("radius") double radius);
}
