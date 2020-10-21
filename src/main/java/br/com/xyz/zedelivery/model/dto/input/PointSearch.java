package br.com.xyz.zedelivery.model.dto.input;


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
        Coordinate coordinate = new Coordinate(coordinates.get(0),coordinates.get(1));
        return geometryFactory.createPoint(coordinate);
    }
}
