package br.com.xyz.zedelivery.model.dto.output;

import br.com.xyz.zedelivery.model.PDV;
import lombok.*;
import org.locationtech.jts.geom.*;

import java.util.List;

@AllArgsConstructor
@Getter
public class PointOutput {

    private final String type;
    private final List<Double> coordinates;

    public PointOutput(PDV pdv) {
        this.type = pdv.getType();
        this.coordinates = List.of(pdv.getLatitude(), pdv.getLongitude());
    }
}
