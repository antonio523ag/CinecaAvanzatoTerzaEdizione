package dev.antoniogrillo.primoesempiocineca.facade.impl;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.automobile.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.AutomobileFacade;
import dev.antoniogrillo.primoesempiocineca.mapper.AutomobileMapper;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import dev.antoniogrillo.primoesempiocineca.service.def.AutomobileService;
import dev.antoniogrillo.primoesempiocineca.service.def.CasaAutomobilisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutomobileFacadeImpl implements AutomobileFacade {

    private final AutomobileService service;
    private final CasaAutomobilisticaService casaService;
    private final AutomobileMapper mapper;

    @Override
    public void save(AggiungiAutomobileDTO automobile) {
        CasaAutomobilistica c=casaService.getById(automobile.getMarcaId());
        Automobile a=mapper.toAutomobile(automobile,c);
        service.save(a);
    }

    @Override
    public AutomobileDTO getById(long id) {
        Automobile a= service.findById(id);
        return mapper.toAutomobileDTO(a);
    }
}
