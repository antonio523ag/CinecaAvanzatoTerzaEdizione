package dev.antoniogrillo.primoesempiocineca.facade.def;

import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginRequestDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginResponseDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.RegistraUtenteDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Utente;

public interface UtenteFacade {
    void aggiungiUtente(RegistraUtenteDTO dto);

    LoginResponseDTO login(LoginRequestDTO dto);

    UtenteDTO converti(Utente utente);
}
