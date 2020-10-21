package br.com.xyz.zedelivery.model.dto.output;

import br.com.xyz.zedelivery.model.*;
import lombok.*;

import java.util.List;
import java.util.stream.*;

@AllArgsConstructor
@Getter
public class CoverageAreaOutput {

    private final String type;
    private final List<Coordinates> coordinates;

    public CoverageAreaOutput(PDV pdv){
        this.type = pdv.getCoverageArea().getGeometryType();
        this.coordinates = Stream.of(pdv.getCoverageArea().getCoordinates()).map(Coordinates::new).collect(Collectors.toList());
    }
}
