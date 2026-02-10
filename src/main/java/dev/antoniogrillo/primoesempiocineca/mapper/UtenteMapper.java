package dev.antoniogrillo.primoesempiocineca.mapper;

import dev.antoniogrillo.primoesempiocineca.dto.utente.RegistraUtenteDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {
    public Utente toUtente(RegistraUtenteDTO dto) {
        Utente u=new Utente();
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setNome(dto.nome());
        u.setCognome(dto.cognome());
        u.setRuolo(dto.ruolo());
        return u;
    }

    public UtenteDTO toUtenteDTO(Utente u) {
        return new UtenteDTO(u.getEmail(),u.getNome(),u.getCognome(),u.getRuolo().name(),u.getId());
    }
}
