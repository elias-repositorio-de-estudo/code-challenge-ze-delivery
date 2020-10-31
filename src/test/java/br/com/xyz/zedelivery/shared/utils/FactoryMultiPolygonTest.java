package br.com.xyz.zedelivery.shared.utils;

import br.com.xyz.zedelivery.pdv.dto.input.*;
import org.junit.jupiter.api.*;
import org.locationtech.jts.geom.MultiPolygon;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FactoryMultiPolygonTest {

    private FactoryMultiPolygon factoryMultiPolygon;
    private ICoverageArea iCoverageArea;

    @BeforeEach
    public void setup(){
        iCoverageArea = mock(ICoverageArea.class);
        factoryMultiPolygon = spy(new FactoryMultiPolygon(iCoverageArea));
    }

    @Test
    public void should_return_optional_empty_when_a_geometry_is_null(){
        Optional<MultiPolygon> geometry = factoryMultiPolygon.createGeometry();
        assertEquals(Optional.empty(),geometry);
    }

}
