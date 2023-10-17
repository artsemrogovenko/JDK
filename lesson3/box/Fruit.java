package lesson3.box;

import java.util.LinkedHashSet;
import java.util.Set;

public interface Fruit {
   float getWeight();
   String getName();
   Set<Fruit> fruits =new LinkedHashSet<>();
}
