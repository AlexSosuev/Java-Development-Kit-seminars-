package generalized_programming.fruit;

public class Orange implements Fruit{
    public static final float WEIGHT = 1.5f;

    @Override
    public String toString() {
        return "Orange";
    }

    @Override
    public float getWeight() {
        return WEIGHT;
    }
}
