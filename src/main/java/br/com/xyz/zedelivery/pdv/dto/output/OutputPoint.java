package br.com.xyz.zedelivery.pdv.dto.output;

import br.com.xyz.zedelivery.pdv.PDV;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
class OutputPoint {

    private final String type;
    private final List<Double> coordinates;

    public OutputPoint(PDV pdv) {
        this.type = pdv.getType();
        this.coordinates = List.of(pdv.getLatitude(), pdv.getLongitude());
    }
}
