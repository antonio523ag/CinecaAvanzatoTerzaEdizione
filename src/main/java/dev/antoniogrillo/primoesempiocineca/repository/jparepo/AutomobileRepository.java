package dev.antoniogrillo.primoesempiocineca.repository.jparepo;

import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//mi occupo di scrivere le query
public interface AutomobileRepository extends JpaRepository<Automobile, Long> {

    List<Automobile> findAllByMarca_Id(long id);
    @Query("select a from Automobile a where a.marca.id = :id")
    List<Automobile> findPerMarcaId(long id);
}
