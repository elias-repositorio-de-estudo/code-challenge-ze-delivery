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
        this.type = pdv.getType();
        this.latitude = pdv.getLatitude();
        this.longitude = pdv.getLongitude();
    }

}
