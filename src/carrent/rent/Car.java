package carrent.rent;

import javax.persistence.*;
import java.beans.Customizer;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by Lukasz on 02.10.2017.
 */
@Entity
public class Car {
    @Id
    @Column(columnDefinition = "INT(11) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    @Enumerated(EnumType.STRING)
    private Make make;
    private int capacity;
    @Embedded
    private Engine engine;
    @Enumerated(EnumType.STRING)
    private CarSegment carSegment;
    @Enumerated(EnumType.STRING)
    private Color color;
    private BigDecimal basePrice;
    private BigDecimal insuranceCost;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns=@JoinColumn(name="car_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="option_id")

    )
    Set<Option> optionSet;
    @OneToMany(mappedBy = "car")
    Set<Rent> rentSet;

    public Car() {
    }

    public Car(String model, Make make, int capacity, Engine engine, CarSegment carSegment, Color color, BigDecimal basePrice, BigDecimal insuranceCost) {
        this.model = model;
        this.make = make;
        this.capacity = capacity;
        this.engine = engine;
        this.carSegment = carSegment;
        this.color = color;
        this.basePrice = basePrice;
        this.insuranceCost = insuranceCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarSegment getCarSegment() {
        return carSegment;
    }

    public void setCarSegment(CarSegment carSegment) {
        this.carSegment = carSegment;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public Set<Option> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<Option> optionSet) {
        this.optionSet = optionSet;
    }

    public boolean rentCar(Customer customer, ZonedDateTime startDate, ZonedDateTime endDate){
        Long days =Duration.between(startDate, endDate).toDays();

        BigDecimal finalPrice = this.getBasePrice().multiply(days < 1 ? BigDecimal.ONE : new BigDecimal(days));
        BigDecimal finalInsuranceCost = this.getInsuranceCost().multiply(days< 1? BigDecimal.ONE : new BigDecimal(days));

        Rent rent = new Rent(customer,startDate,endDate, finalPrice, finalInsuranceCost,
                this, "new rent",false );

        return RentRepository.save(rent);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id == car.id) return true;
        if (capacity != car.capacity) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (make != car.make) return false;
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) return false;
        if (carSegment != car.carSegment) return false;
        if (color != car.color) return false;
        if (basePrice != null ? !basePrice.equals(car.basePrice) : car.basePrice != null) return false;
        return insuranceCost != null ? !insuranceCost.equals(car.insuranceCost) : car.insuranceCost != null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + capacity;
      //  result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (carSegment != null ? carSegment.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
        result = 31 * result + (insuranceCost != null ? insuranceCost.hashCode() : 0);
        return result;
    }
}
