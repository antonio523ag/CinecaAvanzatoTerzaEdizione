package dev.antoniogrillo.primoesempiocineca.facade.def;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.automobile.AutomobileDTO;

public interface AutomobileFacade {
    void save(AggiungiAutomobileDTO automobile);

    AutomobileDTO getById(long id);
}
