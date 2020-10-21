package br.com.xyz.zedelivery.model;

import lombok.*;
import org.locationtech.jts.geom.Coordinate;

@AllArgsConstructor
@Getter
public class Coordinates {

    private final double longitude;
    private final double latitude;

    public Coordinates(Coordinate coordinate) {
        this.longitude = coordinate.x;
        this.latitude = coordinate.y;
    }
}
