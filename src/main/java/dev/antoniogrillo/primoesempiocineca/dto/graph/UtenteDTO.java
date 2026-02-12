package dev.antoniogrillo.primoesempiocineca.dto.graph;

import lombok.Data;

@Data
public class UtenteDTO {
    private long id;
    private String nome;
    private String cognome;
    private String ruolo;
    private String email;
    private String password;
}
