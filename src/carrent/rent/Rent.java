package carrent.rent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Created by Lukasz on 02.10.2017.
 */
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
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

    public Rent() {
    }

    public Rent(Customer customer, ZonedDateTime startDate, ZonedDateTime endDate, BigDecimal rentPrice, BigDecimal insurancePrice, Car car, String description, boolean discount) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentPrice = rentPrice;
        this.insurancePrice = insurancePrice;
        this.car = car;
        this.description = description;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public BigDecimal getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(BigDecimal insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public boolean cancelRent(Customer customer){

        if(canWeCancel(customer)){
            return RentRepository.remove(this);
        }
        return  false;
    }

    public boolean canWeCancel(Customer customer) {
        return customerHasPrivillage(customer) &&
                isRentInFuture();
    }

    public boolean isRentInFuture() {
        return Duration.between(this.getStartDate(), ZonedDateTime.now().plusDays(1)).toDays() > 0;
    }

    public boolean customerHasPrivillage(Customer customer) {
        return this.getCustomer().equals(customer);
    }
}
