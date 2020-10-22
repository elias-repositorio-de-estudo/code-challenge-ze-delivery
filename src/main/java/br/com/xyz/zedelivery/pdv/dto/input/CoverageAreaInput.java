package br.com.xyz.zedelivery.pdv.dto.input;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
class CoverageAreaInput implements ICoverageArea{
    private String type;
    private List<List<List<List<Double>>>> coordinates;
}
