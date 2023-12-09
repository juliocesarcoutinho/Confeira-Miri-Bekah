package br.com.miribekah.model;

import br.com.miribekah.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "endereco")
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1)
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    private Long id;

    @Column
    @NotNull
    @NotBlank(message = "O campo endereço é obrigatório")
    private String logradouro;

    @Column
    @NotNull
    @NotBlank(message = "O CEP é obrigatório")
    @Length(max = 9)
    private String cep;

    @Column
    @NotNull
    @NotBlank(message = "O campo número é obrigatório")
    private String numero;
    
    @Column
    private String complemento;
    
    @Column
    @NotNull
    @NotBlank(message = "O campo bairro é obrigatório")
    private String bairro;
    
    @Column
    @NotNull
    @NotBlank(message = "O campo UF é obrigatório")
    @Length(max = 2)
    private String uf;
    
    @Column(nullable = false, length = 50)
    @NotNull
    @NotBlank(message = "O campo cidade é obrigatório")
    private String cidade;

    @JsonIgnore
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEndereco tipoEndereco;
    
}
