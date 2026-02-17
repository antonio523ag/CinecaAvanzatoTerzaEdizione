package dev.antoniogrillo.primoesempiocineca.graph;

import dev.antoniogrillo.primoesempiocineca.dto.graph.UtenteDTO;
import dev.antoniogrillo.primoesempiocineca.model.Ruolo;
import dev.antoniogrillo.primoesempiocineca.model.Utente;
import dev.antoniogrillo.primoesempiocineca.service.def.GestoreTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphTest {

    @LocalServerPort
    int port;

    private GraphQlTester testerSenzaLogin,testerConLogin;

    @BeforeEach
    public void configure(){
        WebTestClient.Builder builderSenzaUtente = WebTestClient.bindToServer().baseUrl("http://localhost:"+port+"/graphql");
        testerSenzaLogin = HttpGraphQlTester.builder(builderSenzaUtente).build();
        WebTestClient.Builder builderConUtente = WebTestClient.bindToServer().defaultHeader("Authorization","Bearer "+getToken()).baseUrl("http://localhost:"+port+"/graphql");
        testerConLogin = HttpGraphQlTester.builder(builderConUtente).build();
    }

    @Test
    public void testLogin(){
        String body= """
                query Login{
                    login(input: {username: "contactme@antoniogrillo.dev", password:"P4ssw0rd!1"}){
                        token  
                    }
                }
                """;
        testerSenzaLogin.document(body)
                .execute()
                .path("login.utente")
                .pathDoesNotExist();
    }

    @Test
    public void testFindAllUtente(){
        String body= """
                query FindAllUtente {
                    findAllUtente {
                        id
                        nome
                        cognome
                        email
                        ruolo
                        nomeCompleto
                    }
                }
                
                """;
        testerSenzaLogin.document(body)
                .execute()
                .path("findAllUtente[0]")
                .entity(UtenteDTO.class)
                .matches(u->u.getNome().equals("Antonio"));
    }

    @Test
    public void testFindAllUtente2(){
        String body= """
                query FindAllUtente {
                    findAllUtente {
                        id
                        nome
                        cognome
                        email
                        ruolo
                        nomeCompleto
                    }
                }
                
                """;
        testerSenzaLogin.document(body)
                .execute()
                .path("findAllUtente[1].nomeCompleto")
                .entity(String.class)
                .isEqualTo("Antonio Grillo");
    }

    @Autowired
    private GestoreTokenService service;

    private String getToken(){
        Utente u =new Utente();
        u.setEmail("contactme@antoniogrillo.dev");
        u.setNome("Antonio");
        u.setCognome("grillo");
        u.setRuolo(Ruolo.USER);
        return service.generaToken(u);
    }
}
