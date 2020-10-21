package br.com.xyz.zedelivery.pdv;

import br.com.xyz.zedelivery.pdv.dto.input.*;
import br.com.xyz.zedelivery.pdv.dto.output.*;
import br.com.xyz.zedelivery.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PDVController {
    private final PDVRepository pdvRepository;

    @GetMapping("/id/{id}")
    public ResponseEntity findByPDVFor(@PathVariable("id") Long id){
        PDV pdv = pdvRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(new PdvOutput(pdv));
    }

    @GetMapping("/point")
    public ResponseEntity findByPDVBy(@RequestBody PointSearch pointSearch ){
        Point point = pointSearch.createPoint();
        List<PDV> pdvs = pdvRepository.findByAddress(point);
        List<PdvOutput> pdvOutputList = pdvs.stream().map(PdvOutput::new).collect(Collectors.toList());
        return ResponseEntity.ok(pdvOutputList);
    }

    @PostMapping("/createPDV")
    public ResponseEntity create(@RequestBody @Valid PDVInput pdvInput){
        PDV pdv = pdvInput.toPDV();
        pdvRepository.save(pdv);
        return ResponseEntity.ok(pdv);
    }
}
