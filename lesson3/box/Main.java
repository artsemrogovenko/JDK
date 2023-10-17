package lesson3.box;


public class Main {
    public static void main(String[] args) {
        Box<Apple> applebox1 = new Box<Apple>(new Apple(), 3);
        Box<Apple> applebox2 = new Box<Apple>(new Apple(), 6);

        Box<Orange> orangebox = new Box<Orange>(new Orange(), 2);
  
        System.out.println("Количество в applebox1= "+applebox1.getWeight());
        System.out.println("Количество в orangebox1= "+orangebox.getWeight());        
        System.out.println("Вес совпадает? "+applebox1.compare(orangebox));

        applebox2.pullFruits(orangebox);  

        applebox2.pullFruits(applebox1);   
        System.out.println("applebox1= "+applebox1.getWeight()+" applebox2= "+ applebox2.getWeight());     
    }
}
