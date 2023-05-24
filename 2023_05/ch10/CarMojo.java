package ch10;
import java.util.*;
public class CarMojo {
    public static void main(String[] args) {
        List<Car> dieselCars = findCars(Car.cars, c-> !c.isGasoline());
        System.out.println("diesel = " +dieselCars);

        List<Car> oldCars = findCars(Car.cars, c-> c.getAge() > 10 );
        System.out.println("old = " +oldCars);

        List<Car> oldDieselCars = findCars(Car.cars, c-> c.getAge()>10 && !c.isGasoline());
        System.out.println("oldDiesel = " +oldDieselCars);

        System.out.println("dieselCar = ");
        printCars(dieselCars, c -> System.out.printf("%s(%d)", c.getModel(), c.getAge(), c.getMileage()));

        System.out.print("oldCar = ");
        printCars(oldCars, c -> System.out.printf("%s(%d, %d)", c.getModel(), c.getAge(), c.getMileage()));


    }

    public static List<Car> findCars(List<Car> all, CarPredicate cp){
        List<Car> result = new ArrayList<>();

        for(Car car : all){
            if(cp.test(car))
                result.add(car);
        }
        return result;

    }

    public static void printCars(List<Car> all, CarConsumer cc){
        for (Car car : all){
            cc.accept(car);
        }
    }
}
