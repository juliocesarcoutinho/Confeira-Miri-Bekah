package br.com.miribekah.controller;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.model.Acesso;
import br.com.miribekah.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/acessos")
public class AcessoController {
    
    @Autowired
    private AcessoService acessoService;
    
    @PostMapping
    public ResponseEntity<Acesso> adicionar(@RequestBody @Valid Acesso acesso) throws ExcepetionJava {
       
        Acesso acessoSalvo = acessoService.adicionar(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.CREATED);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Acesso> excluirAcesso(@PathVariable Long id) throws ExcepetionJava {
        acessoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
