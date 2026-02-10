package dev.antoniogrillo.primoesempiocineca.facade.def;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.automobile.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;

public interface AutomobileFacade {
    void save(AggiungiAutomobileDTO automobile);

    AutomobileDTO getById(long id);
}
