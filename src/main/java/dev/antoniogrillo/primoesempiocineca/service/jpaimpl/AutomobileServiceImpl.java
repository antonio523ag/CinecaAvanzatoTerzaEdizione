package dev.antoniogrillo.primoesempiocineca.service.jpaimpl;

import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.repository.jparepo.AutomobileRepository;
import dev.antoniogrillo.primoesempiocineca.service.def.AutomobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AutomobileServiceImpl implements AutomobileService {

    //@Autowired
    private final AutomobileRepository repo;

    @Override
    public List<Automobile> findByMarcaId(long id) {
        return repo.findPerMarcaId(id);
    }

    @Override
    public void save(Automobile automobile) {
        repo.save(automobile);
    }

    @Override
    public void delete(long id) {
        repo.deleteById(id);
    }

    @Override
    public Automobile findById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Automobile> findAll() {
        return repo.findAll();
    }

//    public AutomobileServiceImpl(AutomobileRepository repo) {
//        this.repo = repo;
//    }

}
