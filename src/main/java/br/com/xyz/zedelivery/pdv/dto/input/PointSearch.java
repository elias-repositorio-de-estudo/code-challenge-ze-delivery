package br.com.xyz.zedelivery.pdv.dto.input;


import lombok.*;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PointSearch {

    private List<Double> coordinates;

    public Point createPoint(){
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(getLatitude(),getLongitude());
        return geometryFactory.createPoint(coordinate);
    }

    Double getLatitude(){
        return coordinates.get(0);
    }

    Double getLongitude(){
        return coordinates.get(1);
    }
}