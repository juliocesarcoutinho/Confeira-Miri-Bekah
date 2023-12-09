package br.com.miribekah.model;

import br.com.miribekah.enums.StatusContaPagar;
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
@EqualsAndHashCode(of ="id" )
@Table(name = "conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", allocationSize = 1)
public class ContaPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
    private Long id;

    @Column
    @NotNull
    @NotBlank(message = "Preencha a Descrição")
    private String descricao;

    @Column
    @NotNull
    @NotBlank(message = "É necessario preecher o valor Total")
    private BigDecimal valorTotal;
    
    @Column
    private BigDecimal valorDesconto;

    @Column
    @NotNull
    @NotBlank(message = "É necessario preencher o valor de vencimento")
    @Temporal(TemporalType.DATE)
    private Date dtVencimento;
    
    @Column
    @Temporal(TemporalType.DATE)
    private Date dtPagamento;

    @Column
    @NotBlank
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StatusContaPagar status;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "pessoa_fk"))
    private Pessoa pessoa;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_forn_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,
            name = "pessoa_forn_fk"))
    private Pessoa pessoa_fornecedor;
    
}
