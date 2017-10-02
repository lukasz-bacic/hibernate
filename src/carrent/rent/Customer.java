package carrent.rent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Lukasz on 02.10.2017.
 */
@Entity
@Table(name = "user")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthday;
    private LocalDateTime licenseCarDay;
    @Transient
    private Boolean isExperince;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    Set<Rent> rentSet;

    public Customer() {
    }

    public Customer(String firstName, String lastName, LocalDateTime birthday, LocalDateTime licenseCarDay, Boolean isExperince, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.licenseCarDay = licenseCarDay;
        this.isExperince = isExperince;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getLicenseCarDay() {
        return licenseCarDay;
    }

    public void setLicenseCarDay(LocalDateTime licenseCarDay) {
        this.licenseCarDay = licenseCarDay;
    }

    public Boolean getExperince() {
        return isExperince;
    }

    public void setExperince(Boolean experince) {
        isExperince = experince;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Rent> getRentSet() {
        return rentSet;
    }

    public void setRentSet(Set<Rent> rentSet) {
        this.rentSet = rentSet;
    }
}
