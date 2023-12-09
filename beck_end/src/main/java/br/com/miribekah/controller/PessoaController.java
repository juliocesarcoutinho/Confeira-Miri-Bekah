package br.com.miribekah.controller;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.model.PessoaFisica;
import br.com.miribekah.model.PessoaJuridica;
import br.com.miribekah.service.PessoaFisicaService;
import br.com.miribekah.service.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;
    
    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping("/juridica")
    public ResponseEntity<PessoaJuridica> adicionar(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws ExcepetionJava {
        PessoaJuridica novaPessoaJuridica = pessoaJuridicaService.adicionar(pessoaJuridica);
        return new ResponseEntity<>(novaPessoaJuridica, HttpStatus.CREATED);

    }

    @PostMapping("/fisica")
    public ResponseEntity<PessoaFisica> adicionar(@RequestBody @Valid PessoaFisica pessoaFisica) throws ExcepetionJava {
        PessoaFisica novaPessoaFisica = pessoaFisicaService.adicionar(pessoaFisica);
        return new ResponseEntity<>(novaPessoaFisica, HttpStatus.CREATED);

    }

}
