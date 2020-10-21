package br.com.xyz.zedelivery.model.dto.input;

import lombok.*;

@AllArgsConstructor
@Getter
public class PointInput {
    private final String type;
    private final Float lat;
    private final Float lng;
}
