package br.com.miribekah.model;

import br.com.miribekah.enums.TipoPessoa;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa{

    @Column
    @Length(max = 18)
    @NotNull(message = "O campo CNPJ é obrigatório")
    @CNPJ(message = "CNPJ informado está invalido verifique!")
    private String cnpj;
    
    @Column
    @Length(max = 15)
    private String inscEstadual;
    
    @Column
    private String inscMunicipal;
    
    @Column
    private String nomeFantasia;
    
    @Column
    @NotBlank
    @Length(max = 120)
    @NotNull(message = "O campo Razão social é obrigatório")
    private String razaoSocial;
    
    @Column
    private String categoria;

    @Column
    @NotBlank
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TipoPessoa tipoPessoa;
    
}
