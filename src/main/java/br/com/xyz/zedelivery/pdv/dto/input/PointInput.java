package br.com.xyz.zedelivery.pdv.dto.input;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
class PointInput implements IPoint{
    private String type;
    private Double latitude;
    private Double longitude;
}
