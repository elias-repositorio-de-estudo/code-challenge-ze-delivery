package br.com.xyz.zedelivery.model.dto.input;


import br.com.xyz.zedelivery.model.PDV;
import br.com.xyz.zedelivery.shared.exception.JacksonConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.locationtech.jts.geom.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PDVInput {
    private  String tradingName;
    private  String ownerName;
    private  String document;
    private  PointInput address;
    private  CoverageAreaInput coverageArea;

    public PDV toPDV(){
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(address.getLatitude(), address.getLongitude());
        Point point = geometryFactory.createPoint(coordinate);
        ObjectMapper mapper = new JacksonConfig().objectMapper();
        try {
            String json = mapper.writeValueAsString(coverageArea);
            MultiPolygon multiPolygon = mapper.readValue(json, MultiPolygon.class);
            return new PDV(tradingName,ownerName,document,multiPolygon,point);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
