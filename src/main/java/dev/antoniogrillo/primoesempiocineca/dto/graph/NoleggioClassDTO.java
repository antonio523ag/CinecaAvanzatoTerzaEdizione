package dev.antoniogrillo.primoesempiocineca.dto.graph;

import lombok.Data;

@Data
public class NoleggioClassDTO {
    private AutomobileDTO automobile;
    private UtenteDTO utente;

    public NoleggioClassDTO(){}

    public NoleggioClassDTO(AutomobileDTO automobile, UtenteDTO utente){
        this.automobile=automobile;
        this.utente=utente;
    }
}
