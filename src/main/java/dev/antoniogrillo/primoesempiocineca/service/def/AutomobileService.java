package dev.antoniogrillo.primoesempiocineca.service.def;


import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.List;
// mi occupo di controllare il dato
@Validated
public interface AutomobileService {

    List<Automobile> findByMarcaId(@Min(value = 1, message = "l'id della marca deve essere maggiore di 0") long id);
    void save(@Valid Automobile automobile);
    void delete(@Min(value = 1, message = "l'id dell'automobile deve essere maggiore di 0") long id);
    Automobile findById(@Min(value = 1, message = "l'id dell'automobile deve essere maggiore di 0") long id);
    List<Automobile> findAll();

}
