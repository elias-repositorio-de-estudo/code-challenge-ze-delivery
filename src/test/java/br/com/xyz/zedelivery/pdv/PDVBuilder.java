package br.com.xyz.zedelivery.pdv;

import org.locationtech.jts.geom.*;

import java.util.concurrent.atomic.AtomicLong;

class PDVBuilder {

    private static final AtomicLong counter = new AtomicLong();
    private final PDV pdv;

    public PDVBuilder(){
        pdv = new PDV();
    }

    public PDVBuilder withId(){
        this.pdv.setId(counter.getAndIncrement());
        return this;
    }

    public PDVBuilder withTradingName(String name){
        this.pdv.setTradingName(name);
        return this;
    }

    public PDVBuilder withOwnerName(String ownerName){
        this.pdv.setOwnerName(ownerName);
        return this;
    }

    public PDVBuilder withDocument(String document){
        this.pdv.setDocument(document);
        return this;
    }

    public PDVBuilder withCoverageArea(MultiPolygon multiPolygon){
        this.pdv.setCoverageArea(multiPolygon);
        return this;
    }

    public PDVBuilder withPoint(Point point){
        this.pdv.setAddress(point);
        return this;
    }

    public PDV create(){
        return pdv;
    }


}
