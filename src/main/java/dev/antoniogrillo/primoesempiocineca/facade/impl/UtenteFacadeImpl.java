package dev.antoniogrillo.primoesempiocineca.facade.impl;

import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginRequestDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginResponseDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.RegistraUtenteDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.UtenteFacade;
import dev.antoniogrillo.primoesempiocineca.mapper.UtenteMapper;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import dev.antoniogrillo.primoesempiocineca.service.def.GestoreTokenService;
import dev.antoniogrillo.primoesempiocineca.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtenteFacadeImpl implements UtenteFacade {

    private final UtenteService service;
    private final UtenteMapper mapper;
    private final GestoreTokenService tokenService;

    @Override
    public void aggiungiUtente(RegistraUtenteDTO dto) {
        Utente u=mapper.toUtente(dto);
        service.save(u);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        Utente u=service.login(dto.username(),dto.password());
        String token=tokenService.generaToken(u);
        LoginResponseDTO lr=new LoginResponseDTO();
        lr.setToken(token);
        lr.setUtente(mapper.toUtenteDTO(u));
        return lr;
    }

    @Override
    public UtenteDTO converti(Utente utente) {
        return mapper.toUtenteDTO(utente);
    }
}
