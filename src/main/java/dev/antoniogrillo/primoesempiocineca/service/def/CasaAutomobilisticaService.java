package dev.antoniogrillo.primoesempiocineca.service.def;

import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;

import java.util.List;

public interface CasaAutomobilisticaService {
    void save(CasaAutomobilistica casaAutomobilistica);
    List<CasaAutomobilistica> findAll();
    CasaAutomobilistica getById(long id);
}
