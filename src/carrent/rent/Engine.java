package carrent.rent;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * Created by Lukasz on 02.10.2017.
 */
@Embeddable
public class Engine {

    private BigDecimal engineCapacity;
    @Enumerated
    private EngineType engineType;
    private BigDecimal fuelConsumption;
    @Enumerated
    private GearBox gearBox;
    private Integer horsePower;
    private Integer torque;

    public Engine() {
    }

    public Engine(BigDecimal engineCapacity, EngineType engineType, BigDecimal fuelConsumption, GearBox gearBox, Integer horsePower, Integer torque) {
        this.engineCapacity = engineCapacity;
        this.engineType = engineType;
        this.fuelConsumption = fuelConsumption;
        this.gearBox = gearBox;
        this.horsePower = horsePower;
        this.torque = torque;
    }

    public BigDecimal getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(BigDecimal engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public BigDecimal getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(BigDecimal fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public GearBox getGearBox() {
        return gearBox;
    }

    public void setGearBox(GearBox gearBox) {
        this.gearBox = gearBox;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }
}
