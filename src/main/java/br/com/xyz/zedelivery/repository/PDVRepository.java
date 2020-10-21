package br.com.xyz.zedelivery.repository;

import br.com.xyz.zedelivery.model.PDV;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PDVRepository extends JpaRepository<PDV,Long> {
    List<PDV> findByAddress(Point point);
}
