package br.com.miribekah.repository;

import br.com.miribekah.model.PessoaFisica;
import br.com.miribekah.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    
    
    @Query(value = "select pf from PessoaFisica pf where upper(trim(pf.nome)) like %?1%")
    List<PessoaFisica> findByNome(String nome);
    
    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    List<PessoaFisica> findByCpf(String cpf);

    @Query(value = "SELECT pf FROM PessoaFisica pf WHERE pf.cpf = ?1")
    public PessoaFisica existeCpf(String cpf);
    
    @Query(value = "select pf from PessoaFisica pf where pf.email = ?1")
    PessoaFisica findByEmail(String email);
}
