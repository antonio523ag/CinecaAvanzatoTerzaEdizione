package dev.antoniogrillo.primoesempiocineca.facade.def;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.MarcaDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Utente;

import java.util.List;
import java.util.Map;

public interface GraphFacade {
    AutomobileDTO findAutomobileById(long id);

    MarcaDTO findMarcaById(long id);

    UtenteDTO findUtenteById(long id);

    List<AutomobileDTO> findAllAutomobile();

    List<MarcaDTO> findallCasaAutomobilistica();

    List<UtenteDTO> findAllUtente();

    List<UtenteDTO> trovaProprietari(AutomobileDTO automobile);
    Map<AutomobileDTO,List<UtenteDTO>> trovaProprietari(List<AutomobileDTO> automobili);

    void aggiungiAutomobile(AggiungiAutomobileDTO dto);

    List<AutomobileDTO> getAutomobiliNoleggiate(Utente utente);

    String login(String email, String password);

    List<AutomobileDTO> trovaPerMarca(MarcaDTO marca);
}
