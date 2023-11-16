package br.com.miribekah.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "nota_fiscal_compra")
@SequenceGenerator(name = "seq_nota_fiscal_compra", sequenceName = "seq_nota_fiscal_compra", allocationSize = 1, initialValue = 1)
public class NotaFiscalCompra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_compra")
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String numeroNota;
    
    @Column
    @NotNull
    @NotBlank
    private String serieNota;

    @Column
    @NotNull
    @NotBlank
    private String descricaoObs;

    @Column
    @NotNull
    @NotBlank
    private BigDecimal valorTotal;
    
    @Column
    private BigDecimal valorDesconto;

    @Column
    @NotNull
    @NotBlank
    private BigDecimal valorIcms;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pesssoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "pessoa_fk"))
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "conta_pagar_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "conta_pagar_fk"))
    private ContaPagar contaPagar;
    
}
