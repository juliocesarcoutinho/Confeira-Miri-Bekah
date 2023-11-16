package br.com.miribekah.service;

import br.com.miribekah.model.Acesso;
import br.com.miribekah.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {
    
    @Autowired
    AcessoRepository acessoRepository;
    public Acesso adicionar (Acesso acesso){
        return acessoRepository.save(acesso);  
    }
    
}
