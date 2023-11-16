package br.com.miribekah.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1)
public class NotaFiscalVenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String numero;

    @Column
    @NotNull
    @NotBlank
    private String serie;

    @Column
    @NotNull
    @NotBlank
    private String tipo;

    @Column(columnDefinition = "text")
    @NotNull
    @NotBlank
    private String xml;

    @Column(columnDefinition = "text")
    @NotBlank
    private String pdf;

    @OneToOne
    @JoinColumn(name = "venda_compra_loja_virt_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_virt_fk"))
    private VendaCompraLojaVirtual vendaCompraLojaVirtual;
}
