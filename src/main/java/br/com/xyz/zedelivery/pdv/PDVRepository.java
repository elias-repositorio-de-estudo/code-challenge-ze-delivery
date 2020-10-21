package br.com.xyz.zedelivery.pdv;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface PDVRepository extends JpaRepository<PDV,Long> {

    @Query("SELECT p FROM PDV p WHERE CONTAINS (p.coverageArea, :point) = TRUE " +
            "ORDER BY distance(p.address,:point) asc")
    List<PDV> findByAddress(@Param("point") Point point);
}

