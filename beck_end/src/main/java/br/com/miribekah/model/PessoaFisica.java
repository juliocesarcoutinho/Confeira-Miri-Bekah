package br.com.miribekah.model;

import br.com.miribekah.enums.TipoPessoa;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Pessoa{

    @Column
    @Length(max = 14)
    @NotNull(message = "O campo CPF é obrigatório")
    @CPF(message = "CPF informado é invalido verifique!")
    private String cpf;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataNacimento;

    @Column
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TipoPessoa tipoPessoa;
}
