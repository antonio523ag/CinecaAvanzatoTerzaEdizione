package dev.antoniogrillo.primoesempiocineca.service.def;


import dev.antoniogrillo.primoesempiocineca.model.Automobile;

import java.util.List;
// mi occupo di controllare il dato
public interface AutomobileService {

    List<Automobile> findByMarcaId(long id);
    void save(Automobile automobile);
    void delete(long id);
    Automobile findById(long id);
    List<Automobile> findAll();

}
