package com.azvtech.bus_geo_api.repository;

import com.azvtech.bus_geo_api.model.BusStop;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusStopRepository extends JpaRepository<BusStop, Long> {
    /**
     * Busca paradas próximas ao ponto central dentro de um raio específico.
     *
     * @param lonCenter Longitude do ponto central.
     * @param latCenter Latitude do ponto central.
     * @param radius Raio em metros.
     * @return Lista de paradas próximas.
     */
    @Query(value = """
            SELECT bs.fid, bs.stop_name, ST_AsGeoJSON(bs.wkb_geometry) AS geometry
            FROM bus_stop bs
            WHERE ST_DWithin(
                bs.wkb_geometry::geography, 
                ST_SetSRID(ST_MakePoint(:lonCenter, :latCenter), 4326)::geography, 
                :radius
            )
            """, nativeQuery = true)
    List<Object[]> findNearbyStopsWithinRadius(
            @Param("lonCenter") double lonCenter,
            @Param("latCenter") double latCenter,
            @Param("radius") double radius
    );

    // Busca paradas dentro de um raio (metros)
    @Query("SELECT s FROM BusStop s WHERE ST_Distance(s.wkbGeometry, :point) <= :radius")
    List<BusStop> findNearbyStops(@Param("point") Point point, @Param("radius") double radius);
}
