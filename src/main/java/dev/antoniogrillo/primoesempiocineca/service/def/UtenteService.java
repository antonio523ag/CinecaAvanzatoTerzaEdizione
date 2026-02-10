package dev.antoniogrillo.primoesempiocineca.service.def;

import dev.antoniogrillo.primoesempiocineca.model.Utente;

public interface UtenteService {
    void save(Utente utente);
    Utente login(String email, String password);
    Utente getByEmail(String email);
    Utente getById(long id);
}
