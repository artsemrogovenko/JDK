package lesson3.box;

import java.util.ArrayList;
/**
 * Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, 
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины; Для хранения фруктов внутри
 * коробки можете использовать ArrayList;
 */
public class Box <T extends Fruit>{
    private ArrayList<T> fruits=new ArrayList<T>();
    private int count;

    public void setCount(int counts){
        count=counts;
    }

    public int getCount(){
        return count;
    }

    private T getFruits() {
        return fruits.get(0);
    }

    private void setFruits(T e) {
        fruits.clear();
        fruits.add(e);
    }
    public void add(int value){
        count=count+value;
    }

    Box(T fruit){
        fruits.add(fruit);
     }
    Box(T fruit,int counts){
        fruits.add(fruit);
        setCount(counts);
     }

     /**
      * Сделать метод getWeight() который высчитывает вес коробки, зная количество
      * фруктов и вес одного фрукта
      * (вес яблока — 1.0f, апельсина — 1.5f, не важно в каких единицах)
      */
     public float getWeight() {
         return fruits.get(0).getWeight() * count;
     }
    /**
     * Внутри класса коробки сделать метод compare, который позволяет сравнить
     * текущую коробку с той, которую подадут в compare в качестве параметра, 
     * true — если их веса равны, 
     * false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     * @return boolean
     */
    public boolean compare(Box<? extends Fruit> b ){
        return this.getWeight()==b.getWeight();
    }

    /**
     * Написать метод, который позволяет пересыпать фрукты из текущей коробки в
     * другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), 
     * соответственно в текущей коробке фруктов не остается,
     * а в другую перекидываются объекты, которые были в этой коробке
     */

    public void pullFruits(Box<?> source) { // Box<? super T> source
        if (source.getType().equals(this.getType())) {
            this.setCount(this.getCount()+source.getCount());
            this.setFruits(this.getFruits());
            source.setCount(0);
        }else{
            System.out.printf("нельзя смешать %s c %s\n",this.getFruits().getName(),source.getFruits().getName());
        }
    }
    public Class<? extends Fruit> getType(){
        return fruits.get(0).getClass();
    }
}
