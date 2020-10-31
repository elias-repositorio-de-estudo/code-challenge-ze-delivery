package br.com.xyz.zedelivery.pdv.dto.input;


import lombok.*;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Getter
@NoArgsConstructor
public class PointSearch implements IPoint{

    private List<Double> coordinates;
    private String type;

    public double getLatitude(){
        return coordinates.get(0);
    }

    public double getLongitude(){
        return coordinates.get(1);
    }
}
