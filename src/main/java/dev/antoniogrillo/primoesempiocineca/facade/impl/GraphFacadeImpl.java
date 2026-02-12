package dev.antoniogrillo.primoesempiocineca.facade.impl;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.MarcaDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.GraphFacade;
import dev.antoniogrillo.primoesempiocineca.mapper.AutomobileMapper;
import dev.antoniogrillo.primoesempiocineca.mapper.GraphMapper;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import dev.antoniogrillo.primoesempiocineca.service.def.AutomobileService;
import dev.antoniogrillo.primoesempiocineca.service.def.CasaAutomobilisticaService;
import dev.antoniogrillo.primoesempiocineca.service.def.GestoreTokenService;
import dev.antoniogrillo.primoesempiocineca.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GraphFacadeImpl implements GraphFacade {

    private final CasaAutomobilisticaService casaAutomobilisticaService;
    private final AutomobileService automobileService;
    private final UtenteService utenteService;
    private final AutomobileMapper automobileMapper;
    private final GestoreTokenService gestoreTokenService;
    private final GraphMapper graphMapper;

    @Override
    public AutomobileDTO findAutomobileById(long id) {
        Automobile a= automobileService.findById(id);
        return graphMapper.toAutomobileDTO(a);
    }

    @Override
    public MarcaDTO findMarcaById(long id) {
        CasaAutomobilistica c= casaAutomobilisticaService.getById(id);
        return graphMapper.toMarcaDTO(c);
    }

    @Override
    public UtenteDTO findUtenteById(long id) {
        Utente u= utenteService.getById(id);
        return graphMapper.toUtenteDTO(u);
    }

    @Override
    public List<AutomobileDTO> findAllAutomobile() {
        return graphMapper.toAutomobileDTO(automobileService.findAll());
    }

    @Override
    public List<MarcaDTO> findallCasaAutomobilistica() {
        List<CasaAutomobilistica> c= casaAutomobilisticaService.findAll();
        return graphMapper.toMarcaDTO(c);
    }

    @Override
    public List<UtenteDTO> findAllUtente() {
        return graphMapper.toUtenteDTO(utenteService.findAll());
    }

    @Override
    public List<UtenteDTO> trovaProprietari(AutomobileDTO automobile) {
        System.out.println("getProprietari");
        List<Utente> u=utenteService.trovaProprietari(automobile.getId());
        return graphMapper.toUtenteDTO(u);
    }

    @Override
    public Map<AutomobileDTO,List<UtenteDTO>> trovaProprietari(List<AutomobileDTO> automobili) {
        System.out.println("dsf");
        List<Long> ids=automobili.stream().map(AutomobileDTO::getId).toList();
        List<Utente> u= utenteService.trovaProprietari(ids);
        Map<AutomobileDTO,List<UtenteDTO>> m=new HashMap<>();
        for(AutomobileDTO a:automobili){
            List<UtenteDTO> u1=u.stream().filter(ut->ut.getAutomobili().stream().anyMatch(ut1->ut1.getId()==a.getId())).map(graphMapper::toUtenteDTO).toList();
            m.put(a,u1);
        }
        return m;
    }

    @Override
    public void aggiungiAutomobile(AggiungiAutomobileDTO dto) {
        CasaAutomobilistica c=casaAutomobilisticaService.getById(dto.getMarcaId());
        Automobile a=automobileMapper.toAutomobile(dto,c);
        automobileService.save(a);
    }

    @Override
    public List<AutomobileDTO> getAutomobiliNoleggiate(Utente utente) {
        List<Automobile> a=utenteService.getById(utente.getId()).getAutomobili();

        return graphMapper.toAutomobileDTO(a);
    }

    @Override
    public String login(String email, String password) {
        Utente utente=utenteService.login(email,password);
        return gestoreTokenService.generaToken(utente);
    }

    @Override
    public List<AutomobileDTO> trovaPerMarca(MarcaDTO marca) {
        List<Automobile> a=automobileService.findByMarcaId(marca.getId());
        return graphMapper.toAutomobileDTO(a);
    }

    @Override
    public Map<UtenteDTO, List<AutomobileDTO>> trovaUtentiProprietari(List<UtenteDTO> utenti) {
        List<Long> ids=utenti.stream().map(UtenteDTO::getId).toList();
        return utenteService.trovaProprietariMap(ids);
    }
}
