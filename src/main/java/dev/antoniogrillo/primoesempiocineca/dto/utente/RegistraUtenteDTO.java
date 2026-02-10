package dev.antoniogrillo.primoesempiocineca.dto.utente;

import dev.antoniogrillo.primoesempiocineca.model.Ruolo;

public record RegistraUtenteDTO(
        String password,
        String email,
        String nome,
        String cognome,
        Ruolo ruolo
) {
}
