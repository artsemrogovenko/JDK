package lesson3.box;

public class Orange implements Fruit {
    private final float weight = 1.5f;
    private final String name = "Апельсин";
 
    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }
}

