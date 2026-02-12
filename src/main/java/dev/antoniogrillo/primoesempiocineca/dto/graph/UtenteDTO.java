package dev.antoniogrillo.primoesempiocineca.dto.graph;

import dev.antoniogrillo.primoesempiocineca.model.Ruolo;
import lombok.Data;

@Data
public class UtenteDTO {
    private long id;
    private String nome;
    private String cognome;
    private String ruolo;
    private String email;
    private String password;

    public UtenteDTO(){}

    public UtenteDTO(long id, String nome, String cognome, Ruolo ruolo, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo.name();
        this.email = email;
        this.password = password;
    }
}
