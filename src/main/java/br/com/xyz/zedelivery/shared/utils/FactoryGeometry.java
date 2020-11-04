package br.com.xyz.zedelivery.shared.utils;

import br.com.xyz.zedelivery.pdv.dto.input.*;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.*;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FactoryGeometry {

    private final GeometryFactory geometryFactory;

    public FactoryGeometry(GeometryFactory geometryFactory) {
        this.geometryFactory = geometryFactory;
    }

    public Optional<Point> createPoint(IPoint iPoint) {
        Coordinate coordinate = new Coordinate(iPoint.getLatitude(),iPoint.getLongitude());
        Optional<Point> point = Optional.ofNullable(geometryFactory.createPoint(coordinate));
        if(point.isPresent() && point.get().isValid()){
            return point;
        }
        return Optional.empty();
    }

    //REVISAR COM O AQUILES
    //POREM ACHO QUE FAZENDO COM O OBJECT MAPPER FICA MAIS SIMPLES
    public Optional<MultiPolygon> createMultipolygon(ICoverageArea coverageArea) {
        try {


//            String json = mapper.writeValueAsString(coverageArea);
//            return Optional.ofNullable(mapper.readValue(json, MultiPolygon.class));
            String coordinates = coverageArea.getCoordinates()
                    .stream()
                    .map(op -> op.toString())
                    .map(o -> o.replace("[",""))
                    .map(o -> o.replace(",", " "))
                    .map(o -> o.replace("]",","))
                    .map(o -> o.replace(",,,", ","))
                    .map(o -> o.replace(",,", ","))
                    .map(o -> {
                        if(o.endsWith(",")){
                            return o.substring(0,o.length()-1);
                        }
                        return o;
                    })
                    .map(o -> o.concat(")))"))
                    .collect(Collectors.joining());

            String possibleMultiPolygon = "MULTIPOLYGON ((("+coordinates+")))";
            MultiPolygon multiPolygon = (MultiPolygon) new WKTReader().read(possibleMultiPolygon);
            return Optional.ofNullable(multiPolygon);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }
}
