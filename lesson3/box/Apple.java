package lesson3.box;

public class Apple implements Fruit {
    private final float weight = 1.0f;    
    private final String name="Яблоко";

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

    
}
