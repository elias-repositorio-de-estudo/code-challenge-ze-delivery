package br.com.xyz.zedelivery.shared.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FactoryGeometry {

    private final GeometryFactory geometryFactory;
    private final ObjectMapper objectMapper;

    public FactoryGeometry(GeometryFactory geometryFactory, ObjectMapper objectMapper) {
        this.geometryFactory = geometryFactory;
        this.objectMapper = objectMapper;
    }

    public Optional<Point> createPoint(Double latitude, Double longitude) {
        Coordinate coordinate = new Coordinate(latitude,longitude);
        Optional<Point> point = Optional.ofNullable(geometryFactory.createPoint(coordinate));
        if(point.isPresent() && point.get().isValid()){
            return point;
        }
        return Optional.empty();
    }


    public Optional<MultiPolygon> createMultipolygon(List<List<List<List<Double>>>>  coverageArea) {
        try {
            String json = objectMapper.writeValueAsString(coverageArea);
            return Optional.ofNullable(objectMapper.readValue(json, MultiPolygon.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
