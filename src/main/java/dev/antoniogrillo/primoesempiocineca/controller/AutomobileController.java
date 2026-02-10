package dev.antoniogrillo.primoesempiocineca.controller;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.automobile.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.AutomobileFacade;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//mi occupo di esporre gli endpoint
public class AutomobileController {

    private final AutomobileFacade facade;

    @PostMapping("/automobile")
    public ResponseEntity<Void> aggiungiAutomobile(@RequestBody AggiungiAutomobileDTO automobile){
        facade.save(automobile);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/automobile/id/{id}")
    public ResponseEntity<AutomobileDTO> getById(@PathVariable long id){
        return ResponseEntity.ok(facade.getById(id));
    }


}
