package ch09.generic;


public class GenericMojo {
    public class Cup<T>{
        private T beverage;

        public T getBeverage(){
            return beverage;
        }

        public void setBeverage(T beverage) {
            this.beverage = beverage;
        }
    }
}
