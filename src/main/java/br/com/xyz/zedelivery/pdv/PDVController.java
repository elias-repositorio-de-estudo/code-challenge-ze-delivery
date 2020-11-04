package br.com.xyz.zedelivery.pdv;

import br.com.xyz.zedelivery.pdv.dto.input.*;
import br.com.xyz.zedelivery.pdv.dto.output.*;
import br.com.xyz.zedelivery.shared.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PDVController {

    private final PDVRepository pdvRepository;
    private final FactoryGeometry factoryGeometry;

    @GetMapping("/searchBy/{id}")
    public ResponseEntity<OutputPDV> findByPDVFor(@PathVariable("id") Long id){
        PDV pdv = pdvRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(new OutputPDV(pdv));
    }

    @GetMapping("/searchBy/point")
    public ResponseEntity<List<OutputPDV>> findByPDVBy(@RequestBody SearchPoint searchPoint){
        Optional<Point> possiblePoint = factoryGeometry.createPoint(searchPoint);
        if(possiblePoint.isPresent()){
            Point point = possiblePoint.get();
            List<PDV> pdvs = pdvRepository.findByAddress(point);
            List<OutputPDV> outputPDVList = pdvs.stream().map(OutputPDV::new).collect(Collectors.toList());
            return ResponseEntity.ok(outputPDVList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/createPDV")
    public ResponseEntity<OutputPDV> create(@RequestBody @Valid PDVInput pdvInput) {
        Optional<Point> point = factoryGeometry.createPoint(pdvInput.getAddress());
        Optional<MultiPolygon> multiPolygon = factoryGeometry.createMultipolygon(pdvInput.getCoverageArea());

        if(point.isPresent() && multiPolygon.isPresent()){
            PDV pdv = pdvRepository.save(pdvInput.toPDV(point.get(), multiPolygon.get()));
            return ResponseEntity.ok(new OutputPDV(pdv));
        }
        return ResponseEntity.badRequest().build();
    }
}
