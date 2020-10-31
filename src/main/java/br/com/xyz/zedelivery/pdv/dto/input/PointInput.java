package br.com.xyz.zedelivery.pdv.dto.input;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
class PointInput implements IPoint{
    private String type;
    private double latitude;
    private double longitude;
}
