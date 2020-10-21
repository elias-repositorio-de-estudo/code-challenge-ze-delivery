package br.com.xyz.zedelivery.controller;

import br.com.xyz.zedelivery.model.PDV;
import br.com.xyz.zedelivery.model.dto.input.PDVInput;
import br.com.xyz.zedelivery.model.dto.output.PdvOutput;
import br.com.xyz.zedelivery.repository.PDVRepository;
import br.com.xyz.zedelivery.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/point/{point}")
    public ResponseEntity findByPDVBy(@PathVariable String point){
        List<PDV> pdvs = pdvRepository.findByAddress(point);
        List<PdvOutput> collect = pdvs.stream().map(PdvOutput::new).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    @PostMapping("/createPDV")
    public ResponseEntity create(@RequestBody PDVInput pdvInput){
        PDV pdv = pdvInput.toPDV();
        pdvRepository.save(pdv);
        return ResponseEntity.ok(pdv);
    }
}
