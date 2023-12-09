package br.com.miribekah.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    private Long id;
    
    @Column
    @NotNull
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 4, max = 150)
    private String nome;
    
    @Column(nullable = false)
    @NotNull
    @NotBlank(message = "O campo email é obrigatório")
    @Email
    @Size(max = 120)
    private String email;
    
    @Column(nullable = false)
    @NotNull
    @NotBlank(message = "O campo email é obrigatório")
    @Size(max = 16)
    private String telefone;
    
    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<>();
    
}
