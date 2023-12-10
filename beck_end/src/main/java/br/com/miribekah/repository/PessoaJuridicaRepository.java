package br.com.miribekah.repository;

import br.com.miribekah.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    @Query(value = "SELECT pj FROM PessoaJuridica pj WHERE upper(trim(pj.nome)) like %?1%")
    List<PessoaJuridica> pesquisaPorNome(String nome);
    
    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    PessoaJuridica findByCnpj(String cnpj);
    
    @Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
    PessoaJuridica findByInscEstadual(String ie);
    
    @Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
    PessoaJuridica findByEmail(String email);

    @Query(value = "SELECT pj FROM PessoaJuridica pj WHERE pj.cnpj = ?1")
    List<PessoaJuridica> existeCnpjList(String cnpj);

    @Query(value = "SELECT pj FROM PessoaJuridica pj WHERE pj.inscEstadual = ?1")
    PessoaJuridica existeIe(String inscEstadual);

    @Query(value = "SELECT pj FROM PessoaJuridica pj WHERE pj.inscEstadual = ?1")
    List<PessoaJuridica> existeIeList(String inscEstadual);
    
}
