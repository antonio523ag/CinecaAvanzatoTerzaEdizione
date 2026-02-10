package dev.antoniogrillo.primoesempiocineca.repository.criteria;

import dev.antoniogrillo.primoesempiocineca.dto.internal.AutomobileParameter;
import dev.antoniogrillo.primoesempiocineca.dto.internal.Classi;
import dev.antoniogrillo.primoesempiocineca.dto.internal.Condizioni;
import dev.antoniogrillo.primoesempiocineca.dto.internal.QueryParam;
import dev.antoniogrillo.primoesempiocineca.model.Automobile;
import dev.antoniogrillo.primoesempiocineca.model.CasaAutomobilistica;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AutomobileRepository {

    private final EntityManager manager;

    public void salva(Automobile automobile){
        manager.persist(automobile);
    }

    public void elimina(long id){
        Automobile automobile = manager.find(Automobile.class, id);
        if(automobile != null){
            manager.remove(automobile);
        }
    }
    public Automobile trovaPerId(long id){
        try {
            return manager.find(Automobile.class, id);
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Automobile> trovaAutomobili(AutomobileParameter... parametri){
        return creaQuery(parametri).getResultList();
    }

    public Automobile trovaAutomobile(AutomobileParameter... parametri){
        try {
            return creaQuery(parametri).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    private TypedQuery<Automobile> creaQuery(AutomobileParameter... parametri){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> cq = cb.createQuery(Automobile.class);
        Root<Automobile> root = cq.from(Automobile.class);
        Join<CasaAutomobilistica, Automobile> marche = root.join("marca");
        Join<Utente,Automobile> proprietari = root.join("proprietari");
        List<Predicate> predicati = new ArrayList<>();
        for(var param : parametri){
            Predicate p=switch (param.classe()){
                case "marca" -> cb.equal(marche.get(param.parametro()), param.valore());
                case "proprietario" -> cb.equal(proprietari.get(param.parametro()), param.valore());
                case "auto"-> cb.equal(root.get(param.parametro()), param.valore());
                default -> null;
            };
            if(p!=null)predicati.add(p);
        }
        cq.where(predicati.toArray(new Predicate[0]));
        return manager.createQuery(cq);
    }

    private <P>TypedQuery<Automobile> creaQuery(QueryParam<P>... parametri){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> cq = cb.createQuery(Automobile.class);
        Root<Automobile> root = cq.from(Automobile.class);
        Join<CasaAutomobilistica, Automobile> marche = root.join("marca");
        Join<Utente,Automobile> proprietari = root.join("proprietari");
        Predicate p=null;
        for(var condizione : parametri){
            Predicate p1;
            if(condizione.classi()==Classi.UTENTE){
                p1=cb.equal(proprietari.get(condizione.parametro()), condizione.valore());
            }else if (condizione.classi()==Classi.AUTOMOBILE){
                p1=cb.equal(root.get(condizione.parametro()), condizione.valore());
            }else if (condizione.classi()==Classi.CASA_AUTOMOBILISTICA){
                p1=cb.equal(marche.get(condizione.parametro()), condizione.valore());
            }else throw new IllegalArgumentException("Classe non supportata");

            if(p==null)p=p1;
            else{
                if(condizione.condizioni()== Condizioni.AND)p=cb.and(p,p1);
                else p=cb.or(p,p1);
            }

        }
        if(p!=null)cq.where(p);
        return manager.createQuery(cq);
    }

    public Automobile findById(long id){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Automobile> cq = cb.createQuery(Automobile.class);
        Root<Automobile> root = cq.from(Automobile.class);
        cq.where(cb.equal(root.get("id"), id));
        return manager.createQuery(cq).getSingleResult();
    }



}
