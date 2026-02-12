package dev.antoniogrillo.primoesempiocineca.service.def;

import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CasaAutomobilisticaService {
    void save(@Valid CasaAutomobilistica casaAutomobilistica);
    List<CasaAutomobilistica> findAll();
    CasaAutomobilistica getById(@Min(value = 1, message = "l'id della casa automobilistica deve essere maggiore di 0") long id);
}
