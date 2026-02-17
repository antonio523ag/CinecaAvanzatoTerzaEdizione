package dev.antoniogrillo.primoesempiocineca.utentecontroller;

import dev.antoniogrillo.primoesempiocineca.PrimoEsempioCinecaApplication;
import dev.antoniogrillo.primoesempiocineca.dto.utente.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration(classes = PrimoEsempioCinecaApplication.class)
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testLoginSenzaBody() throws Exception {
        RequestBuilder rb= MockMvcRequestBuilders.post("/login");
        ResultActions r=mock.perform(rb);
        ResultMatcher rm1= MockMvcResultMatchers.status().isBadRequest();
        r.andExpect(rm1);
    }

    @Test
    public void testConBodyGiusto() throws Exception{
        String json=getRequest("contactme@antoniogrillo.dev","P4ssw0rd!1");
        RequestBuilder rb= MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON).content(json);
        ResultActions r=mock.perform(rb);
        ResultMatcher rm1= MockMvcResultMatchers.status().isOk();
        ResultMatcher rm2=MockMvcResultMatchers.jsonPath("$.username").exists();
        ResultMatcher rm3=MockMvcResultMatchers.jsonPath("$.username").isString();
        ResultMatcher rm4=MockMvcResultMatchers.jsonPath("$.username").value("contactme@antoniogrillo.dev");
        r.andExpect(rm1).andExpect(rm2).andExpect(rm3).andExpect(rm4);
    }







    private String getRequest(String username,String password){
        LoginRequestDTO l=new LoginRequestDTO(username,password);
        return mapper.writeValueAsString(l);
    }
}
