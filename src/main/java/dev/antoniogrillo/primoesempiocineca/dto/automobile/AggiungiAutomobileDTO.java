package dev.antoniogrillo.primoesempiocineca.dto.automobile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AggiungiAutomobileDTO {
    private long marcaId;
    private String modello;
    private String targa;
}
