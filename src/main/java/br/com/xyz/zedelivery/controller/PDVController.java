package br.com.xyz.zedelivery.controller;

import br.com.xyz.zedelivery.model.PDV;
import br.com.xyz.zedelivery.repository.PDVRepository;
import br.com.xyz.zedelivery.shared.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PDVController {
    private final PDVRepository pdvRepository;

    @GetMapping("/{id}")
    public ResponseEntity findByPDVFor(@PathVariable("id") Long id){
        PDV pdv = pdvRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(pdv);
    }
}