package dev.antoniogrillo.primoesempiocineca.repository.jparepo;

import dev.antoniogrillo.primoesempiocineca.dto.graph.NoleggioClassDTO;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    @Query(nativeQuery = true, value = "select * from utente where email = :email and password = :password")
    Optional<Utente> loginSQL(String email,String password);

    @Query("select u from Utente u where u.email = :email and u.password = :password")
    Optional<Utente> loginJPQL(String email,String password);

    Optional<Utente> findByEmailAndPassword(String email,String password);

    Optional<Utente> findByEmail(String nonSo);

    @Query(nativeQuery = true, value = "select * from utente u join proprietari_automobili p on p.utente_fk=u.id where p.automobile_fk in :lista")
    List<Utente> trovaProprietari(List<Long> lista);

    @Query(nativeQuery = true, value = "select * from utente u join proprietari_automobili p on p.utente_fk=u.id where p.automobile_fk = :idAutomobile")
    List<Utente> trovaProprietari(long idAutomobile);


    @Query(value = "select new dev.antoniogrillo.primoesempiocineca.dto.graph.NoleggioClassDTO(new dev.antoniogrillo.primoesempiocineca.dto.graph.AutomobileDTO(a.id,a.targa,a.modello),new dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO(u.id,u.nome,u.cognome,u.ruolo,u.email,u.password))  from Utente u join Automobile a where u.id in :idUtenti")
    List<NoleggioClassDTO> trovaNoleggiClass(List<Long> idUtenti);

}
