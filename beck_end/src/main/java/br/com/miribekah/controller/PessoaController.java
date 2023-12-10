package br.com.miribekah.controller;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.dto.CepDTO;
import br.com.miribekah.dto.ConsultaCnpjDTO;
import br.com.miribekah.model.PessoaFisica;
import br.com.miribekah.model.PessoaJuridica;
import br.com.miribekah.service.PessoaFisicaService;
import br.com.miribekah.service.PessoaJuridicaService;
import br.com.miribekah.service.PessoaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;
    
    @Autowired
    private PessoaFisicaService pessoaFisicaService;
    
    @Autowired
    private PessoaUserService pessoaUserService;

    @ResponseBody
    @GetMapping(value = "/consultaCep/{cep}")
    public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep) {
        CepDTO cepDTO = pessoaUserService.consultaCep(cep);
        return new ResponseEntity<>(cepDTO, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/consultaCnpjWs/{cnpj}")
    public ResponseEntity<ConsultaCnpjDTO> consultaCnpjWs(@PathVariable("cnpj") String cnpj) {
        ConsultaCnpjDTO consultaCnpjDTO = pessoaUserService.consultaCnpjWs(cnpj);
        return new ResponseEntity<>(consultaCnpjDTO, HttpStatus.OK);

    }
    
    /*Pessoa Juridica*/
    @ResponseBody
    @GetMapping(value = "/consultaNomePj/{nome}")
    public ResponseEntity <List<PessoaJuridica>> consultaNomePj(@PathVariable("nome") String nome){
        List<PessoaJuridica> pessoaJuridicas = pessoaJuridicaService.listarPorNome(nome.trim().toUpperCase());
        return new ResponseEntity<List<PessoaJuridica>>(pessoaJuridicas, HttpStatus.OK);
    }

    /*Lista uma pessoa Juridica por cnpj*/
    @ResponseBody
    @GetMapping("/listarPjCnpj/{cnpj}")
    public ResponseEntity<List<PessoaJuridica>> consultaCnpj(@PathVariable("cnpj") String cnpj){
        List<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.listarPorCnpj(cnpj);
        return new ResponseEntity<>(pessoaJuridica, HttpStatus.OK);

    }

    @PostMapping("/juridica")
    public ResponseEntity<PessoaJuridica> adicionar(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws ExcepetionJava {
        PessoaJuridica novaPessoaJuridica = pessoaJuridicaService.adicionar(pessoaJuridica);
        return new ResponseEntity<>(novaPessoaJuridica, HttpStatus.CREATED);

    }
    
    @GetMapping("/consultaPfNome/{nome}")
    public ResponseEntity<List<PessoaFisica>> listarPorNome(@PathVariable("nome") String nome){
       List<PessoaFisica> pfNome = pessoaFisicaService.listarPorNome(nome.trim().toUpperCase());
       return new ResponseEntity<>(pfNome, HttpStatus.OK);
    }

    @GetMapping("/consultaPorCpf/{cpf}")
    public ResponseEntity<List<PessoaFisica>> listarPorCpf(@PathVariable("cpf") String cpf){
        List<PessoaFisica> pfCpf = pessoaFisicaService.listarPorCpf(cpf);
        return new ResponseEntity<>(pfCpf, HttpStatus.OK);
    }

    @PostMapping("/fisica")
    public ResponseEntity<PessoaFisica> adicionar(@RequestBody @Valid PessoaFisica pessoaFisica) throws ExcepetionJava {
        PessoaFisica novaPessoaFisica = pessoaFisicaService.adicionar(pessoaFisica);
        return new ResponseEntity<>(novaPessoaFisica, HttpStatus.CREATED);

    }

}
