package dev.antoniogrillo.primoesempiocineca.mapper;

import dev.antoniogrillo.primoesempiocineca.dto.casaautomobilistica.AggiungiMarcaDTO;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import org.springframework.stereotype.Component;

@Component
public class CasaAutomobilisticaMapper {

    public CasaAutomobilistica toCasaAutomobilistica(AggiungiMarcaDTO casaAutomobilistica) {
        CasaAutomobilistica c=new CasaAutomobilistica();
        c.setNome(casaAutomobilistica.getCasaAutomobilistica());
        return c;
    }
}
