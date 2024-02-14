package generalized_programming.fruit;

public class Apple implements Fruit{
    public static final float WEIGHT = 1.0f;

    @Override
    public String toString() {
        return "Apple";
    }

    @Override
    public float getWeight() {
        return WEIGHT;
    }
}