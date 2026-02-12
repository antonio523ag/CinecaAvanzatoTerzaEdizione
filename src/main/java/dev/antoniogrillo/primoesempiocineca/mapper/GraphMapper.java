package dev.antoniogrillo.primoesempiocineca.mapper;

import dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.MarcaDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphMapper {
    public UtenteDTO toUtenteDTO(Utente u){
        UtenteDTO dto=new UtenteDTO();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCognome(u.getCognome());
        dto.setRuolo(u.getRuolo().name());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
        return dto;
    }

    public List<UtenteDTO> toUtenteDTO(List<Utente> utenti){
        return utenti.stream().map(this::toUtenteDTO).toList();
    }

    public AutomobileDTO toAutomobileDTO(Automobile a){
        AutomobileDTO dto=new AutomobileDTO();
        dto.setId(a.getId());
        dto.setTarga(a.getTarga());
        dto.setModello(a.getModello());
        return dto;
    }

    public List<AutomobileDTO> toAutomobileDTO(List<Automobile> automobili){
        return automobili.stream().map(this::toAutomobileDTO).toList();
    }

    public MarcaDTO toMarcaDTO(CasaAutomobilistica m){
        MarcaDTO dto=new MarcaDTO();
        dto.setId(m.getId());
        dto.setNome(m.getNome());
        return dto;
    }

    public List<MarcaDTO> toMarcaDTO(List<CasaAutomobilistica> marche){
        return marche.stream().map(this::toMarcaDTO).toList();
    }
}
