package dev.antoniogrillo.primoesempiocineca.casaautomobilisticacontroller;

import dev.antoniogrillo.primoesempiocineca.PrimoEsempioCinecaApplication;
import dev.antoniogrillo.primoesempiocineca.dto.casaautomobilistica.AggiungiMarcaDTO;
import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration(classes = PrimoEsempioCinecaApplication.class)
@AutoConfigureMockMvc
public class AggiungiMarcaTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testAggiungiMarcaSenzaToken() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/admin/marca").content(creaMarca("Fiat")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testAggiungiMarcaSenzaDiritti() throws Exception{
        mock.perform(
                MockMvcRequestBuilders.post("/admin/marca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(creaMarca("Fiat"))
                        .header("Authorization","Bearer "+tokenUtente())
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testAggiungiMarcaConDiritti() throws Exception{
        mock.perform(
                MockMvcRequestBuilders.post("/admin/marca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(creaMarca("Fiat"))
                        .header("Authorization","Bearer "+tokenAdmin())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAggiungiMarcaConWith() throws Exception {
        RequestBuilder rb=MockMvcRequestBuilders.post("/admin/marca")
                .with(SecurityMockMvcRequestPostProcessors.user("a.grillo@gmail.com"))
                .content(creaMarca("Opel"))
                .contentType(MediaType.APPLICATION_JSON);
        mock.perform(rb).andExpect(MockMvcResultMatchers.status().isOk());
    }









    private String creaMarca(String marca){
        AggiungiMarcaDTO a=new AggiungiMarcaDTO();
        a.setCasaAutomobilistica(marca);
        return mapper.writeValueAsString(a);

    }


    private String tokenUtente() throws Exception {
        String json=loginRequestDTO("contactme@antoniogrillo.dev","P4ssw0rd!1");
        return getToken(json);
    }

    private String tokenAdmin() throws Exception {
        String json=loginRequestDTO("a.grillo@gmail.com","P4ssw0rd!1");
        return getToken(json);
    }

    private String getToken(String json) throws Exception {
        RequestBuilder rb= MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON).content(json);
        ResultActions r=mock.perform(rb);
        return r.andReturn().getResponse().getHeader("Authorization");
    }

    private String loginRequestDTO(String email,String password){
        return mapper.writeValueAsString(new LoginRequestDTO(email,password));
    }

}
