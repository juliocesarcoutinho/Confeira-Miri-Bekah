package br.com.miribekah.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "status_rastreio")
@SequenceGenerator(name = "seq_status_rastreio", sequenceName = "seq_status_rastreio", allocationSize = 1)
public class StatusRastreio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreio")
    private Long id;
    
    @Column
    private String centroDistribuicao;
    
    @Column
    private String cidade;
    
    @Column
    private String estado;
    
    @Column
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "venda_compra_loja_virtual_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_virt_fk"))
    private VendaCompraLojaVirtual vendaCompraLojaVirtual;
    
}
