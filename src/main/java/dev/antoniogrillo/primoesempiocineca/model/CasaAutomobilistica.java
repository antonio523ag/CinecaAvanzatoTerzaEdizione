package dev.antoniogrillo.primoesempiocineca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "marca")
public class CasaAutomobilistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "il nome non può essere vuoto")
    private String nome;
    @OneToMany(mappedBy = "marca",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Automobile> automobili;
}
