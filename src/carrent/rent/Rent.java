package carrent.rent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by Lukasz on 02.10.2017.
 */
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private BigDecimal rentPrice;
    private BigDecimal insurancePrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Car car;
    private String description;
    private boolean discount;



}
