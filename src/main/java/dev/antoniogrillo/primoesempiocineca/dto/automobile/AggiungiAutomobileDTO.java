package dev.antoniogrillo.primoesempiocineca.dto.automobile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AggiungiAutomobileDTO {
    @Min(value = 1,message = "non esistono id minori di 1")
    private long marcaId;
    @NotBlank(message = "il modello non può essere vuoto")
    private String modello;
    @NotBlank(message = "la targa non può essere vuota")
    @Size(min = 7,max = 7,message = "la targa deve essere di 7 caratteri")
    private String targa;
}
