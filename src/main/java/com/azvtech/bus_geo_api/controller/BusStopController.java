package com.azvtech.bus_geo_api.controller;

import com.azvtech.bus_geo_api.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stops")
public class BusStopController {

    @Autowired
    private BusStopRepository busStopRepository;

    /**
     * Endpoint para buscar paradas próximas dentro de um raio.
     */
    @GetMapping("/nearby-within-radius")
    public List<Map<String, Object>> findNearbyStopsWithinRadius(
            @RequestParam double lonCenter,
            @RequestParam double latCenter,
            @RequestParam double radius) {

        // Chama o método do repositório
        List<Object[]> results = busStopRepository.findNearbyStopsWithinRadius(lonCenter, latCenter, radius);

        // Transforma os resultados em uma lista de mapas
        return results.stream().map(result -> {
            Map<String, Object> stop = new HashMap<>();
            stop.put("fid", result[0]);
            stop.put("stop_name", result[1]);
            stop.put("geometry", result[2]);
            return stop;
        }).toList();
    }
}
