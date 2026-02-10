package dev.antoniogrillo.primoesempiocineca.mapper;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.automobile.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import org.springframework.stereotype.Component;

@Component
public class AutomobileMapper {
    public Automobile toAutomobile(AggiungiAutomobileDTO automobile, CasaAutomobilistica c) {
        Automobile a=new Automobile();
        a.setTarga(automobile.getTarga());
        a.setModello(automobile.getModello());
        a.setMarca(c);
        return a;
    }

    public AutomobileDTO toAutomobileDTO(Automobile a) {
        AutomobileDTO dto=new AutomobileDTO();
        dto.setMarca(a.getMarca().getNome());
        dto.setIdMarca(a.getMarca().getId());
        dto.setModello(a.getModello());
        dto.setId(a.getId());
        dto.setTarga(a.getTarga());
        return dto;
    }
}
