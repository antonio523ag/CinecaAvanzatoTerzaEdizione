package dev.antoniogrillo.primoesempiocineca.facade.impl;

import dev.antoniogrillo.primoesempiocineca.dto.casaautomobilistica.AggiungiMarcaDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.CasaAutomobilisticaFacade;
import dev.antoniogrillo.primoesempiocineca.mapper.CasaAutomobilisticaMapper;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import dev.antoniogrillo.primoesempiocineca.service.def.CasaAutomobilisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CasaAutomobilisticaFacadeImpl implements CasaAutomobilisticaFacade {

    private final CasaAutomobilisticaService service;
    private final CasaAutomobilisticaMapper mapper;

    @Override
    public void save(AggiungiMarcaDTO casaAutomobilistica) {
        CasaAutomobilistica c=mapper.toCasaAutomobilistica(casaAutomobilistica);
        service.save(c);
    }

    @Override
    public List<CasaAutomobilistica> findAll() {
        return service.findAll();
    }

    @Override
    public CasaAutomobilistica getById(Long id) {
        return service.getById(id);
    }
}
