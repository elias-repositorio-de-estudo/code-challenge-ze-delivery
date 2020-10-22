package br.com.xyz.zedelivery.pdv.dto.input;

import java.util.List;

public interface ICoverageArea {
    String getType();
    List<List<List<List<Double>>>> getCoordinates();
}
