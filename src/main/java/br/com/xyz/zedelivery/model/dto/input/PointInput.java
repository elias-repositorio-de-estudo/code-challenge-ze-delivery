package br.com.xyz.zedelivery.model.dto.input;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PointInput {
    private String type;
    private Float latitude;
    private Float longitude;
}
