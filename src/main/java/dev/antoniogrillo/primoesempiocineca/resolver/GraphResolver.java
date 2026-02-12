package dev.antoniogrillo.primoesempiocineca.resolver;

import dev.antoniogrillo.primoesempiocineca.dto.automobile.AggiungiAutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.MarcaDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.facade.def.GraphFacade;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@EnableMethodSecurity
public class GraphResolver {

    private final GraphFacade facade;

   /*
   findAutomobileById(id:ID!):Automobile!
    findMarcaById(id:ID!):CasaAutomobilistica!
    findUtenteById(id:ID!):Utente!
    findAllAutomobile:[Automobile!]!
    findAllMarca:[CasaAutomobilistica!]!
    findAllUtente:[Utente!]!
    */

    @QueryMapping
    public AutomobileDTO findAutomobileById(@Argument long id) {
        return facade.findAutomobileById(id);
    }

    @QueryMapping
    public MarcaDTO findMarcaById(@Argument long id) {
        return facade.findMarcaById(id);
    }

    @QueryMapping
    public UtenteDTO findUtenteById(@Argument long id) {
        return facade.findUtenteById(id);
    }

    @QueryMapping
    public List<AutomobileDTO> findAllAutomobile() {
        return facade.findAllAutomobile();
    }

    @QueryMapping
    public List<MarcaDTO> findAllMarca() {
        return facade.findallCasaAutomobilistica();
    }

    @QueryMapping
    public List<UtenteDTO> findAllUtente() {
        return facade.findAllUtente();
    }


    @SchemaMapping(typeName = "CasaAutomobilistica", field = "automobili")
    public List<AutomobileDTO> trovaPerMarca(MarcaDTO marca) {
        return facade.trovaPerMarca(marca);
    }

    @MutationMapping
    public boolean aggiungiAutomobile(@Argument AggiungiAutomobileDTO dto) {
        facade.aggiungiAutomobile(dto);
        return true;
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<AutomobileDTO> getAutomobiliNoleggiate(@AuthenticationPrincipal Utente utente) {
        return facade.getAutomobiliNoleggiate(utente);
    }

    @QueryMapping
    public String login(@Argument String email, @Argument String password) {
        return facade.login(email, password);
    }

    @BatchMapping(field = "proprietari", typeName = "Automobile")
    public Map<AutomobileDTO, List<UtenteDTO>> automobili(List<AutomobileDTO> automobili) {
        return facade.trovaProprietari(automobili);
    }

    @SchemaMapping(typeName = "Utente")
    public String nomeCompleto(UtenteDTO utente) {
        return utente.getNome() + " " + utente.getCognome();
    }
}
