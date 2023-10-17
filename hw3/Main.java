package hw3;

public class Main {
    public static void main(String[] args) {
        MyCompare comparator = new MyCompare();
        Calculator calc=new Calculator();
        Pair<String,Integer> pair = new Pair<String,Integer>("2", 4);
           
        int a =  (int) calc.divide(6.2, 3);
        double b = calc.divide(6, 3);

        System.out.println(comparator.compareArrays(new String[] { "1", "3" }, new String[] { "1", "3" }));
        System.out.println(comparator.compareArrays(new Integer[] { 1, 3 }, new String[] { "1", "3" }));
        System.out.println(comparator.compareArrays(new Integer[] { 1, 3 }, new Integer[] { 1, 3 }));
        System.out.println(comparator.compareArrays(new Integer[] { 1, 3 }, new Integer[] { 1, 3, 4 }));

        System.out.println(pair.getFirst().getClass());
        System.out.println(pair.getSecond().getClass());
        System.out.println(pair.toString()); 
        String test=pair.getFirst();
}}
