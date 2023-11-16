package br.com.miribekah.repository;

import br.com.miribekah.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    @Query("SELECT a FROM Acesso a WHERE UPPER(TRIM(a.descricao)) LIKE %?1%")
    List<Acesso> buscarAcessoDesc(String desc);
}
