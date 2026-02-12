package dev.antoniogrillo.primoesempiocineca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utente implements UserDetails {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome_utente")
    @NotBlank(message = "il nome non può essere vuoto")
    private String nome;
    @NotBlank(message = "il cognome non può essere vuoto")
    private String cognome;
    @Column(unique = true,insertable = true,updatable = false)
    @Email(message = "deve essere un indirizzo email valido")
    @NotNull(message = "l'email non può essere vuota")
    private String email;
    @NotNull(message = "il ruolo non può essere vuoto")
    private Ruolo ruolo;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "la password deve contenere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale")
    @NotNull(message = "la password non può essere vuota")
    private String password;
    @ManyToMany
    @JoinTable(name = "proprietari_automobili",
            joinColumns = @JoinColumn(name = "utente_fk",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "automobile_fk",nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"automobile_fk", "utente_fk"}, name = "proprietario_unico"))
    private List<Automobile> automobili;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + ruolo.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
