package br.com.xyz.zedelivery.pdv.dto.output;

import br.com.xyz.zedelivery.pdv.PDV;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
class PointOutput {

    private final String type;
    private final List<Double> coordinates;

    public PointOutput(PDV pdv) {
        this.type = pdv.getType();
        this.coordinates = List.of(pdv.getLatitude(), pdv.getLongitude());
    }
}
