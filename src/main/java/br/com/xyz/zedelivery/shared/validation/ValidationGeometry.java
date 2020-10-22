package br.com.xyz.zedelivery.shared.validation;

import org.locationtech.jts.geom.Geometry;

import java.util.List;

public class ValidationGeometry {

    public boolean isValid(List<Geometry> geometries){
        geometries.stream().filter(geometry -> !geometry.isValid()).
    }
}
