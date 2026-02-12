package dev.antoniogrillo.primoesempiocineca.service.jpaimpl;

import dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.NoleggioClassDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import dev.antoniogrillo.primoesempiocineca.repository.jparepo.UtenteRepository;
import dev.antoniogrillo.primoesempiocineca.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;


    @Override
    public void save(Utente utente) {
        repo.save(utente);
    }

    @Override
    public Utente login(String email, String password) {
        return repo.findByEmailAndPassword(email,password).orElse(null);
    }

    @Override
    public Utente getByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public Utente getById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Utente> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Utente> trovaProprietari(List<Long> ids) {
        return repo.trovaProprietari(ids);
    }


    @Override
    public Map<UtenteDTO,List<AutomobileDTO>> trovaProprietariMap(List<Long> ids){
        List<NoleggioClassDTO> l=repo.trovaNoleggiClass(ids);
        Map<UtenteDTO,List<AutomobileDTO>> m=new HashMap<>();
        for(var n:l){
            UtenteDTO u=n.getUtente();
            List<AutomobileDTO> l1=m.get(u);
            if(l1==null)l1=new ArrayList<>(List.of(n.getAutomobile()));
            else l1.add(n.getAutomobile());
            m.put(u,l1);
        }
        return m;
    }

    @Override
    public List<Utente> trovaProprietari(long idAutomobile) {
        return repo.trovaProprietari(idAutomobile);
    }
}
