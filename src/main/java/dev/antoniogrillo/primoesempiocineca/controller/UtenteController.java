package dev.antoniogrillo.primoesempiocineca.controller;

import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginRequestDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginResponseDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.RegistraUtenteDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.UtenteFacade;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteFacade facade;

    @PostMapping("/utente")
    public ResponseEntity<Void> aggungiUtente(@RequestBody RegistraUtenteDTO dto){
        facade.aggiungiUtente(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UtenteDTO> login(@RequestBody LoginRequestDTO dto){
        LoginResponseDTO utente = facade.login(dto);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", utente.getToken()).body(utente.getUtente());
    }

    @GetMapping("/user")
    public ResponseEntity<UtenteDTO> getUtente(@AuthenticationPrincipal Utente utente){
        UtenteDTO u= facade.converti(utente);
        return ResponseEntity.ok(u);
    }
}
