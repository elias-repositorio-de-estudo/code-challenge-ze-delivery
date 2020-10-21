package br.com.xyz.zedelivery.repository;

import br.com.xyz.zedelivery.model.PDV;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;

public interface PDVRepository extends JpaRepository<PDV,Long> {

    @Query("SELECT p FROM PDV p WHERE CONTAINS (p.coverageArea, :point) = TRUE")
    List<PDV> findByAddress(@Param("point") Point point);
}
