package dev.antoniogrillo.primoesempiocineca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "il modello non può essere vuoto")
    private String modello;
    @NotBlank(message = "la targa non può essere vuota")
    @Size(min = 7,max = 7,message = "la targa deve essere di 7 caratteri")
    private String targa;
    @ManyToOne
    @JoinColumn(name ="marca_fk", nullable = false)
    @NotNull(message = "la marca non può essere nulla")
    private CasaAutomobilistica marca;

    @ManyToMany(mappedBy = "automobili")
    private List<Utente> proprietari;
}
