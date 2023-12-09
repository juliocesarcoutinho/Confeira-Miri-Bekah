package br.com.miribekah.model;

import br.com.miribekah.enums.StatusContaReceber;
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
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", allocationSize = 1, initialValue = 1)
public class ContaReceber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
    private Long id;

    @Column
    @NotNull
    @NotBlank(message = "É necessario preencher a descrição")
    private String descricao;
    
    @Column
    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private StatusContaReceber status;

    @Column
    @NotNull
    @NotBlank(message = "É necessario preencher a data de vencimento")
    @Temporal(TemporalType.DATE)
    private Date dtVencimento;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date dtPagamento;
    
    @Column
    @NotNull
    @NotBlank(message = "Insira o valor total")
    private BigDecimal valorTotal;

    @Column
    private BigDecimal valorDesconto;

    @ManyToOne(targetEntity = Pessoa.class) /* Muitas contas para uma pessoa*/
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "pessoa_fk"))
    private Pessoa pessoa;
    
}
