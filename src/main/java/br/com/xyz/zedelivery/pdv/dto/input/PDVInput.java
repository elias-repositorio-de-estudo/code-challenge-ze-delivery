package br.com.xyz.zedelivery.pdv.dto.input;


import br.com.xyz.zedelivery.pdv.PDV;
import lombok.*;
import org.locationtech.jts.geom.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PDVInput {
    @NotNull
    private String tradingName;
    @NotNull
    private String ownerName;
    @NotNull
    private String document;
    private PointInput address;
    private CoverageAreaInput coverageArea;

    public PDV toPDV(Point point, MultiPolygon multiPolygon){
        return new PDV(tradingName,ownerName,document,multiPolygon,point);
    }

}
