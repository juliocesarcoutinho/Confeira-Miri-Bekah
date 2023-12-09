package br.com.miribekah.repository;

import br.com.miribekah.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
    
    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    PessoaJuridica findByCnpj(String cnpj);
    
    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    PessoaJuridica findByInscEstadual(String ie);
    
    @Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
    PessoaJuridica findByEmail(String email);
    
}
