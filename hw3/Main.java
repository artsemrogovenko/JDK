package hw3;

public class Main {
    public static void main(String[] args) {
        MyCompare comparator = new MyCompare();
        Calculator calc=new Calculator();
        Pair pair=new Pair<>("1",2);
           
        System.out.println(calc.divide(2, 3));
        System.out.println(calc.divide(6, 3));

        System.out.println(comparator.compareArrays(new String[] { "1", "3" }, new String[] { "1", "3" }));
        System.out.println(comparator.compareArrays(new Integer[] { 1, 3 }, new String[] { "1", "3" }));
        System.out.println(comparator.compareArrays(new Integer[] { 1, 3 }, new Integer[] { 1, 3 }));
        System.out.println(comparator.compareArrays(new Integer[] { 1, 3 }, new Integer[] { 1, 3, 4 }));

        System.out.print(pair.getFirst());
        System.out.print(pair.getSecond());
        System.out.print(pair.toString()); 
    }
}
