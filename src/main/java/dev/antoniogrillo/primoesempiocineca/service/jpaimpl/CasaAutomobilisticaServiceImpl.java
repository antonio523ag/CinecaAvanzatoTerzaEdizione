package dev.antoniogrillo.primoesempiocineca.service.jpaimpl;

import dev.antoniogrillo.primoesempiocineca.exception.CustomResponseException;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import dev.antoniogrillo.primoesempiocineca.repository.jparepo.CasaAutomobilisticaRepository;
import dev.antoniogrillo.primoesempiocineca.service.def.CasaAutomobilisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CasaAutomobilisticaServiceImpl implements CasaAutomobilisticaService {

    private final CasaAutomobilisticaRepository repo;

    @Override
    public void save(CasaAutomobilistica casaAutomobilistica) {
        repo.save(casaAutomobilistica);
    }

    @Override
    public List<CasaAutomobilistica> findAll() {
        return repo.findAll();
    }

    @Override
    public CasaAutomobilistica getById(long id) {
        return repo.findById(id).orElseThrow(()->new CustomResponseException(HttpStatus.NOT_FOUND, "Casa automobilistica non trovata con id: " + id));
    }
}
