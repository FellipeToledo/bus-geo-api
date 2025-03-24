package com.azvtech.bus_geo_api.controller;

import com.azvtech.bus_geo_api.model.BusRoute;
import com.azvtech.bus_geo_api.repository.BusRouteRepository;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class BusRouteController {

    @Autowired
    private BusRouteRepository busRouteRepository;

    /**
     * Filtra rotas que intersectam uma área geográfica (polígono em formato WKT).
     */
    @PostMapping("/within")
    public List<BusRoute> findRoutesWithinArea(@RequestBody String wktPolygon) throws ParseException {
        WKTReader reader = new WKTReader();
        Geometry area = reader.read(wktPolygon); // Ler geometria WKT
        return busRouteRepository.findRoutesWithinArea(area);
    }
}
