package dev.antoniogrillo.primoesempiocineca.service.def;

import dev.antoniogrillo.primoesempiocineca.model.Utente;

public interface GestoreTokenService {

    String generaToken(Utente utente);
    boolean verificaToken(String token);
    Utente getUtenteDaToken(String token);
}
