package dev.antoniogrillo.primoesempiocineca.facade.def;

import dev.antoniogrillo.primoesempiocineca.dto.casaautomobilistica.AggiungiMarcaDTO;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;

import java.util.List;

public interface CasaAutomobilisticaFacade {
    void save(AggiungiMarcaDTO casaAutomobilistica);

    List<CasaAutomobilistica> findAll();

    CasaAutomobilistica getById(Long id);
}
