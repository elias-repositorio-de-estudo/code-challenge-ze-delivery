package br.com.xyz.zedelivery.controller;

import br.com.xyz.zedelivery.model.PDV;
import br.com.xyz.zedelivery.model.dto.input.PDVInput;
import br.com.xyz.zedelivery.repository.PDVRepository;
import br.com.xyz.zedelivery.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PDVController {
    private final PDVRepository pdvRepository;

    @GetMapping("/{id}")
    public ResponseEntity findByPDVFor(@PathVariable("id") Long id){
        PDV pdv = pdvRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(pdv);
    }

    @GetMapping("/{point}")
    public ResponseEntity findByPDVBy(@RequestBody Point point){
        List<PDV> pdvs = pdvRepository.findByAddress(point);
        return ResponseEntity.ok(pdvs);
    }

    @PostMapping("/createPDV")
    public ResponseEntity create(@RequestBody PDVInput pdvInput){
        PDV pdv = pdvInput.toPDV();
        pdvRepository.save(pdv);
        return ResponseEntity.ok(pdv);
    }
}
