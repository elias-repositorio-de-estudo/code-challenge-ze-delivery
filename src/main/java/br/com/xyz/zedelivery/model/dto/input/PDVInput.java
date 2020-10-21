package br.com.xyz.zedelivery.model.dto.input;


import br.com.xyz.zedelivery.model.PDV;
import br.com.xyz.zedelivery.shared.utils.JacksonConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public PDV toPDV(){
        Point point = createPoint();
        ObjectMapper mapper = JacksonConfig.objectMapper();
        try {
            MultiPolygon multiPolygon = createMultipolygon(mapper);
            return new PDV(tradingName,ownerName,document,multiPolygon,point);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Point createPoint(){
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(address.getLatitude(), address.getLongitude());
        return geometryFactory.createPoint(coordinate);
    }

    private MultiPolygon createMultipolygon(ObjectMapper mapper) throws JsonProcessingException {
        String json = mapper.writeValueAsString(coverageArea);
        return mapper.readValue(json, MultiPolygon.class);
    }

}
