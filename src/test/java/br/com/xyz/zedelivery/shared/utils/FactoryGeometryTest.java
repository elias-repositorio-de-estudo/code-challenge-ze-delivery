package br.com.xyz.zedelivery.shared.utils;

import br.com.xyz.zedelivery.pdv.dto.input.*;
import org.junit.jupiter.api.*;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FactoryGeometryTest {

    @Autowired
    private GeometryFactory geometryFactory;
    private FactoryGeometry factoryGeometry;
    private IPoint iPoint;
    private ICoverageArea iCoverageArea;

    @BeforeEach
    public void setup(){
        factoryGeometry = spy(new FactoryGeometry(geometryFactory));
        iPoint = mock(IPoint.class);
        iCoverageArea = mock(ICoverageArea.class);
    }

    @Test
    public void should_create_a_point(){
        when(iPoint.getLatitude()).thenReturn(-46.57421);
        when(iPoint.getLongitude()).thenReturn(-21.785741);
        when(iPoint.getType()).thenReturn("Point");
        Optional<Point> optionalPoint = factoryGeometry.createPoint(iPoint);
        Point point = optionalPoint.get();
        assertEquals(iPoint.getLatitude(),point.getX());
        assertEquals(iPoint.getLongitude(),point.getY());
        assertEquals(iPoint.getType(),point.getGeometryType());
        assertEquals(point.isValid(),true);
    }

    @Test
    public void should_return_a_point_default(){
        Optional<Point> optionalPoint = factoryGeometry.createPoint(iPoint);
        Point point = optionalPoint.get();
        assertEquals(point.getX(),0);
        assertEquals(point.getY(),0);
        assertEquals(point.getGeometryType(),"Point");
        assertEquals(point.isValid(),true);
    }

}
