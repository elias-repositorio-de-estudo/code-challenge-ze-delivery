package br.com.xyz.zedelivery.model.dto.output;

import br.com.xyz.zedelivery.model.PDV;
import lombok.*;

@AllArgsConstructor
@Getter
public class PointOutput {

    private final String type;
    private final Double latitude;
    private final Double longitude;

    public PointOutput(PDV pdv) {
        this.type = pdv.getAddress().getGeometryType();
        this.latitude = pdv.getAddress().getY();
        this.longitude = pdv.getAddress().getX();
    }

}
