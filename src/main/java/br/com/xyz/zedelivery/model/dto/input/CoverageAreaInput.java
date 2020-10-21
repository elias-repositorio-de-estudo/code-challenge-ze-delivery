package br.com.xyz.zedelivery.model.dto.input;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoverageAreaInput {
    private  String type;
    private  List<List<Double>> coordinates;
}
