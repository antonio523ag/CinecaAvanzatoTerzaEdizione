package dev.antoniogrillo.primoesempiocineca.dto.utente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String token;
    private UtenteDTO utente;
}
