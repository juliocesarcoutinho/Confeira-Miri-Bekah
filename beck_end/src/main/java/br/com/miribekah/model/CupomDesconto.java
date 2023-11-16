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
@Table(name = "cupom_desconto")
@SequenceGenerator(name = "seq_cupom_desconto", sequenceName = "seq_cupom_desconto", allocationSize = 1)
public class CupomDesconto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    private Long id;

    @Column
    @NotNull
    @NotBlank 
    private String codigo_desconto;
    
    @Column
    private BigDecimal valorRealDesconto;
    
    @Column
    private BigDecimal valorPorcentagemDesconto;

    @Column
    @NotBlank
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataValidadeCupom;
    
}
