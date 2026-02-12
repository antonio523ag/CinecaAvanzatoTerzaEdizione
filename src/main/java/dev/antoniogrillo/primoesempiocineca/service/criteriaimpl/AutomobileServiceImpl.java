package dev.antoniogrillo.primoesempiocineca.service.criteriaimpl;

import dev.antoniogrillo.primoesempiocineca.dto.internal.AutomobileParameter;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.repository.criteria.AutomobileCriteriaRepository;
import dev.antoniogrillo.primoesempiocineca.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileCriteriaRepository repository;


    @Override
    public List<Automobile> findByMarcaId(long id) {
        return repository.trovaAutomobili(new AutomobileParameter("marca","id",""+id));
    }

    @Override
    public void save(Automobile automobile) {
        repository.salva(automobile);
    }

    @Override
    public void delete(long id) {
        repository.elimina(id);
    }

    @Override
    public Automobile findById(long id) {
        return repository.trovaAutomobile(new AutomobileParameter("auto","id",""+id));
    }

    @Override
    public List<Automobile> findAll() {
        return repository.trovaAutomobili();
    }

    public List<Automobile> trovaAutoPossedute(String email){
        return repository.trovaAutomobili(new AutomobileParameter("utente","email",email));
    }

    public List<Automobile> trovaAutoPossedute(String nome,String cognome){
        return repository.trovaAutomobili(new AutomobileParameter("utente","nome",nome),new AutomobileParameter("utente","cognome",cognome));
    }
}
