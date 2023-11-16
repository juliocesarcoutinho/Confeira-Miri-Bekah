package br.com.miribekah.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;
    
    @Column
    @NotNull
    @NotBlank
    private String tipoUnidade;
    
    @Column
    @NotNull
    @NotBlank
    private String nome;
    
    @Column(columnDefinition = "text", length = 2000)
    private String descricao;

    /* Nota item Produto Associar */

    @Column
    @NotNull
    @NotBlank
    private Double peso;

    @Column
    @NotNull
    @NotBlank
    private Double largura;

    @Column
    @NotNull
    @NotBlank
    private Double altura;

    @Column
    @NotNull
    @NotBlank
    private Double profundidade;

    @Column
    @NotNull
    @NotBlank
    private BigDecimal valorVenda = BigDecimal.ZERO;

    @Column
    @NotNull
    @NotBlank
    private Integer qtdEstoque = 0;
    
    @Column
    private Integer qtdAlertaEstoque = 0;
    
    @Column
    private String linkYoutube;
    
    @Column
    private Boolean alertaQtdEstoque = Boolean.FALSE;
    
    @Column
    private Boolean ativo = Boolean.TRUE;
    
    @Column
    private Integer alertaQtdClique = 0;
    
}
