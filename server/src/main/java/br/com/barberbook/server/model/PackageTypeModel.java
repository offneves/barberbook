package br.com.barberbook.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "tb_package_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageTypeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "barber_shop_id", nullable = false)
    @JsonIgnore
    private BarbershopModel barbershop;
    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}
