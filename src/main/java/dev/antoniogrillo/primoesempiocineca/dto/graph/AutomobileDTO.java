package dev.antoniogrillo.primoesempiocineca.dto.graph;

import lombok.Data;

@Data
public class AutomobileDTO {
    private long id;
    private String targa;
    private String modello;

    public AutomobileDTO(){}

    public AutomobileDTO(long id, String targa, String modello) {
        this.id = id;
        this.targa = targa;
        this.modello = modello;
    }
}
