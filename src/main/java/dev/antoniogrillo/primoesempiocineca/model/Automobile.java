package dev.antoniogrillo.primoesempiocineca.model;

import jakarta.persistence.*;
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
    private String modello;
    private String targa;
    @ManyToOne
    @JoinColumn(name ="marca_fk", nullable = false)
    private CasaAutomobilistica marca;
    @ManyToMany
    @JoinTable(name = "proprietari_automobili",
            joinColumns = @JoinColumn(name = "automobile_fk",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "utente_fk",nullable = false),
    uniqueConstraints = @UniqueConstraint(columnNames = {"automobile_fk", "utente_fk"}, name = "proprietario_unico"))
    private List<Utente> proprietari;
}
