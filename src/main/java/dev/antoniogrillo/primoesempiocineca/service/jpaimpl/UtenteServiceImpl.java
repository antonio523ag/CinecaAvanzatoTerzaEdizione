package dev.antoniogrillo.primoesempiocineca.service.jpaimpl;

import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import dev.antoniogrillo.primoesempiocineca.repository.jparepo.UtenteRepository;
import dev.antoniogrillo.primoesempiocineca.service.def.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Utente> trovaProprietari(long idAutomobile) {
        return repo.trovaProprietari(idAutomobile);
    }
}
