package br.com.xyz.zedelivery.pdv.dto.output;

import br.com.xyz.zedelivery.pdv.PDV;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;
import java.util.stream.*;

@AllArgsConstructor
@Getter
class CoverageAreaOutput {

    private final String type;
    private final List<List<Double>> coordinates;

    public CoverageAreaOutput(PDV pdv){
        this.type = pdv.getCoverageArea().getGeometryType();
        this.coordinates = Stream.of(pdv.getCoordinates()).map(this::createListCoordinate).collect(Collectors.toList());
    }

    public List<Double> createListCoordinate(Coordinate coordinate){
        return List.of(coordinate.getX(),coordinate.getY());
    }
}
