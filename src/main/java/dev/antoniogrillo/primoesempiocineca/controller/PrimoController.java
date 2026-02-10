package dev.antoniogrillo.primoesempiocineca.controller;

import dev.antoniogrillo.primoesempiocineca.model.Utente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PrimoController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello3")
    public ResponseEntity<String> hello3() {
        int i=1;
        if(i==1) {
            return ResponseEntity.ok("Hello World!");
        }else if(i==2) {
            return ResponseEntity.status(404).body("Hello World!");
        }else{
            return new ResponseEntity<>("ciao a tutti",HttpStatus.OK);
        }
    }

    @GetMapping("/hello4") //http://localhost:8080/api/hello4?name=antonio&cognome=grillo
    public ResponseEntity<Void> hello4(@RequestParam("name") String nome,@RequestParam String cognome){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hello5/{name}/{cognome}")
    public ResponseEntity<Void> hello5(@PathVariable("name") String nome,@PathVariable String cognome){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hello6")
    public ResponseEntity<Void> hello6(@RequestBody Utente utente){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hello7")
    public ResponseEntity<Utente> hello7(){
        Utente utente=new Utente();
        utente.setNome("Antonio");
        utente.setCognome("Grillo");
        utente.setEmail("contactme@antoniogrillo.dev");
        utente.setPassword("P4ssw0rd!1");
        utente.setId(1);
        return ResponseEntity.ok(utente);
    }


}
