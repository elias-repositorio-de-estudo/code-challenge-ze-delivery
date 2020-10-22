package br.com.xyz.zedelivery.pdv;

import br.com.xyz.zedelivery.pdv.dto.input.*;
import br.com.xyz.zedelivery.pdv.dto.output.*;
import br.com.xyz.zedelivery.shared.exception.NotFoundException;
import br.com.xyz.zedelivery.shared.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PDVController {
    private final PDVRepository pdvRepository;

    @GetMapping("/searchBy/{id}")
    public ResponseEntity<PdvOutput> findByPDVFor(@PathVariable("id") Long id){
        PDV pdv = pdvRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(new PdvOutput(pdv));
    }

    @GetMapping("/searchBy/point")
    public ResponseEntity<List<PdvOutput>> findByPDVBy(@RequestBody PointSearch pointSearch ){
        Optional<Point> possiblePoint = new FactoryPoint(pointSearch).createGeometry();
        if(possiblePoint.isPresent()){
            Point point = possiblePoint.get();
            List<PDV> pdvs = pdvRepository.findByAddress(point);
            List<PdvOutput> pdvOutputList = pdvs.stream().map(PdvOutput::new).collect(Collectors.toList());
            return ResponseEntity.ok(pdvOutputList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/createPDV")
    public ResponseEntity create(@RequestBody @Valid PDVInput pdvInput) throws JsonProcessingException {
        Optional<Point> point = new FactoryPoint(pdvInput.getAddress()).createGeometry();
        Optional<MultiPolygon> multiPolygon = new FactoryMultiPolygon(pdvInput.getCoverageArea()).createGeometry();

        PDV pdv = pdvInput.toPDV(point.get(),multiPolygon.get());
        pdvRepository.save(pdv);
        return ResponseEntity.ok(pdv);
    }
}
