package dev.antoniogrillo.primoesempiocineca.service.def;

import dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Validated
public interface UtenteService {
    void save(@Valid Utente utente);
    Utente login(@Email(message = "l'email deve essere valida") String email,
                 @NotBlank(message = "la password non può essere vuota")
                 @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "la password deve contenere almeno 8 caratteri, una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale")
                 String password);
    Utente getByEmail(@NotNull(message = "l'email non può essere nulla")@Email(message = "l'email deve essere valida") String email);
    Utente getById(@Min(value = 1, message = "l'id dell'utente deve essere maggiore di 0") long id);

    List<Utente> findAll();

    List<Utente> trovaProprietari(List<Long> ids);

    Map<UtenteDTO,List<AutomobileDTO>> trovaProprietariMap(List<Long> idUtenti);

    List<Utente> trovaProprietari(long idAutomobile);
}
