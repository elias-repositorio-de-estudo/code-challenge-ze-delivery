package br.com.xyz.zedelivery.model.dto.input;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
public class CoverageAreaInput {
    private final String type;
    private final List<List<List<List<Float>>>> coordinates;
}
