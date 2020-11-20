package br.com.xyz.zedelivery.shared.utils;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.*;

@Configuration
public class BeanGeometryFactory {
    @Bean
    public GeometryFactory createGeometryFactoryBean(){
        return new GeometryFactory();
    }
}
