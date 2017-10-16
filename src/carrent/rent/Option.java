package carrent.rent;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Lukasz on 02.10.2017.
 */
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(
            joinColumns=@JoinColumn(name="option_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="car_id")
    )
    Set<Car> carSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carSet) {
        this.carSet = carSet;
    }
}
