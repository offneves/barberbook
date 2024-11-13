package br.com.barberbook.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "tb_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserModel user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "barber_shop", nullable = false)
    @JsonIgnore
    private BarbershopModel barberShop;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "package_type_id", nullable = false)
    @JsonIgnore
    private PackageTypeModel packageType;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "payment", nullable = false)
    private String payment;
    @Column(name = "coupon")
    private String coupon;

}
