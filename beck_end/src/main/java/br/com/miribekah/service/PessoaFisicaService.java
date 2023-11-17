package br.com.miribekah.service;

import br.com.miribekah.model.PessoaFisica;
import br.com.miribekah.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {
    
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    
    public PessoaFisica adicionar(PessoaFisica pessoaFisica){        
        return pessoaFisicaRepository.save(pessoaFisica);
    }
    
}
