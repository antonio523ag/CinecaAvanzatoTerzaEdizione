package dev.antoniogrillo.primoesempiocineca.dto.automobile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutomobileDTO {
    private String marca;
    private String modello;
    private long id;
    private String targa;
    private long idMarca;
}
