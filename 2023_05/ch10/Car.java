package ch10;

import java.util.Arrays;
import java.util.List;

public class Car {
    private String model; private boolean gasoline;
    private int age; private int mileage;

    public Car(String model, boolean gasoline, int age, int mileage){
        this.model = model; this.gasoline = gasoline; this.age = age; this.mileage = mileage;
    }
    public String getModel(){ return model; }
    public boolean isGasoline(){ return gasoline; }
    public int getAge(){ return age; }
    public int getMileage(){ return mileage; }

    public String toString(){
        return String.format("Car(%s, %s, %d, %d)", model,gasoline,age,mileage);
    }

    public static final List<Car> cars = Arrays.asList(
            new Car("Sonata",true,15,210000),
            new Car("Grand",true,10,200000),
            new Car("Taxi",true,18,220000),
            new Car("CatTaxi",true,6,10000),
            new Car("Bus",true,1,7000));
}
