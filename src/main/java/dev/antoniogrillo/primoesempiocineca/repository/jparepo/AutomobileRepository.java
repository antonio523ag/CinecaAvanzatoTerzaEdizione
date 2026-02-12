package dev.antoniogrillo.primoesempiocineca.repository.jparepo;

import dev.antoniogrillo.primoesempiocineca.dto.graph.NoleggioClassDTO;
import dev.antoniogrillo.primoesempiocineca.dto.graph.NoleggioDTO;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//mi occupo di scrivere le query
public interface AutomobileRepository extends JpaRepository<Automobile, Long> {

    List<Automobile> findAllByMarca_Id(long id);
    @Query("select a from Automobile a where a.marca.id = :id")
    List<Automobile> findPerMarcaId(long id);

    @Query(nativeQuery = true, value = "select u.nome as nomeUtente,u.cognome as cognomeUtente, u.email from automobile a join proprietari_automobili p on p.automobile_fk = a.id join Utente u on p.utente_fk = u.id")
    List<NoleggioDTO> trovaNoleggi();


}
