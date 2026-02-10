package dev.antoniogrillo.primoesempiocineca.controller;

import dev.antoniogrillo.primoesempiocineca.dto.casaautomobilistica.AggiungiMarcaDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.CasaAutomobilisticaFacade;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CasaAutomobilisticaController {

    private final CasaAutomobilisticaFacade facade;

    @PostMapping("/marca")
    public ResponseEntity<Void> save(@RequestBody AggiungiMarcaDTO casaAutomobilistica) {
        facade.save(casaAutomobilistica);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/marche")
    public ResponseEntity<List<CasaAutomobilistica>> findAll(){
        return  ResponseEntity.status(200).body(facade.findAll());
    }

    @GetMapping("/marca/id/{id}")
    public ResponseEntity<CasaAutomobilistica> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(facade.getById(id));
    }
}
