package dev.antoniogrillo.primoesempiocineca.dto.utente;

public record UtenteDTO(
        String username,
        String nome,
        String cognome,
        String ruolo,
        long id
) {
}
