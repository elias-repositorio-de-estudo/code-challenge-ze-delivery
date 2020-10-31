package br.com.xyz.zedelivery.shared.utils;

import br.com.xyz.zedelivery.pdv.dto.input.IPoint;
import org.locationtech.jts.geom.*;

import javax.swing.text.html.Option;
import java.util.Optional;

public class FactoryPoint{

    private final IPoint iPoint;

    public FactoryPoint(IPoint iPoint){
        this.iPoint = iPoint;
    }

    public Optional<Point> createGeometry() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(iPoint.getLatitude(),iPoint.getLongitude());
        Optional<Point> point = Optional.ofNullable(geometryFactory.createPoint(coordinate));
        if(point.isPresent() && point.get().isValid()){
            return point;
        }
        return Optional.empty();
    }

}
