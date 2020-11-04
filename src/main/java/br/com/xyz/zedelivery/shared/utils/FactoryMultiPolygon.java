package br.com.xyz.zedelivery.shared.utils;

import br.com.xyz.zedelivery.pdv.dto.input.ICoverageArea;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.*;

import java.util.Optional;

public class FactoryMultiPolygon{

    private final ICoverageArea coverageArea;

    public FactoryMultiPolygon(ICoverageArea coverageArea) {
        this.coverageArea = coverageArea;
    }

    public Optional<MultiPolygon> createGeometry() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(coverageArea.getCoordinates());
            return Optional.ofNullable(mapper.readValue(json, MultiPolygon.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}


