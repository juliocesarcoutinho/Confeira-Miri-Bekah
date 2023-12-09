package br.com.miribekah.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "categoria_produto")
@SequenceGenerator(name = "seq_categoria_produto", sequenceName = "seq_categoria_produto", allocationSize = 1)
public class CategoriaProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_produto")
    private Long id;

    @Column
    @NotNull
    @NotBlank(message = "Preencha a Descrição")
    private String nomeDescricao;

}
