package br.com.miribekah.service;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.model.Acesso;
import br.com.miribekah.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcessoService {
    
    @Autowired
    AcessoRepository acessoRepository;
    public Acesso adicionar (Acesso acesso) throws ExcepetionJava {
        if (acesso.getId() == null) {
            List<Acesso> acessos = acessoRepository.buscarAcessoDesc(acesso.getDescricao().toUpperCase());
            if (!acessos.isEmpty()){
                throw new ExcepetionJava("Ja existe um acesso com a mesma descrição " + acesso.getDescricao());
            }
        }
        return acessoRepository.save(acesso);  
    }
    
    public void remover(Long id) throws ExcepetionJava {
        try {
            acessoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ExcepetionJava(String.format("Cidade com código %d não encontrada", id));
        } catch (DataIntegrityViolationException e) {
            throw new ExcepetionJava(String.format("Cidade de código %d não pode ser removida, pois esta em uso", id));
        }
    }
    
}
