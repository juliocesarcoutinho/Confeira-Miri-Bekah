package br.com.miribekah.controller;

import br.com.miribekah.model.Acesso;
import br.com.miribekah.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acessos")
public class AcessoController {
    
    @Autowired
    private AcessoService acessoService;
    
    @PostMapping
    public ResponseEntity<Acesso> adicionar(@RequestBody Acesso acesso){
        
        Acesso acessoSalvo = acessoService.adicionar(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.CREATED);
        
    }
}
