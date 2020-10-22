package br.com.xyz.zedelivery.pdv;


import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.locationtech.jts.geom.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.stream.Stream;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PDV {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String tradingName;
    @NotNull
    private String ownerName;
    @CNPJ
    @Column(unique = true)
    private String document;
    @Column(columnDefinition = "geometry")
    private MultiPolygon coverageArea;
    @Column(columnDefinition = "geometry")
    private Point address;

    public PDV(String tradingName, String ownerName, String document, MultiPolygon multiPolygon, Point point) {
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = multiPolygon;
        this.address = point;
    }

    public Double getLatitude(){
        return address.getX();
    }

    public Double getLongitude(){
        return address.getY();
    }

    public String getType(){
        return address.getGeometryType();
    }

    public Coordinate[] getCoordinates(){
        return coverageArea.getCoordinates();
    }

    public String getCoverageAreaType() {
        return coverageArea.getGeometryType();
    }
}
