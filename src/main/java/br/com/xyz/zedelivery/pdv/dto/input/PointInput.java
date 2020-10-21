package br.com.xyz.zedelivery.pdv.dto.input;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
class PointInput {
    private String type;
    private Float latitude;
    private Float longitude;
}
