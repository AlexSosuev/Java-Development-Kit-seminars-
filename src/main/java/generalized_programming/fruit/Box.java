package generalized_programming.fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit>{
    private final List<T> container;

    Box(){
        container = new ArrayList<>();
    }

    void add(T fruit){
        container.add(fruit);
    }

    void print(){
        System.out.println(getWeight());
    }

    public T get(int index){
        return container.get(index);
    }

    float getWeight(){
        return container.get(0).getWeight() * container.size();
    }

    boolean compareByWeight(Box<?> with){
        return getWeight() == with.getWeight();
    }

    void transferTo(Box<? super T> dest){
        dest.container.addAll(container);
        this.container.clear();
    }

    @Override
    public String toString() {
        return container.toString();
    }
}