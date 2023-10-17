package hw3;

/** 
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: 
 * sum(), multiply(), divide(), subtract(). 
 * Параметры этих методов – два числа разного типа (но необязательно разного между собой), над которыми должна быть произведена операция.
 */
public class Calculator {

    public <T,V> Number sum(T  a, T b) {
       return conversion(a, b, "+");
    }

    public <T,V> Number multiply(T a, T b) {
        return conversion(a, b, "*");
    }

    public <T,V> double divide(T a, V b) {
        return conversion(a, b, "/");
    }

    public <T,V> Number subtract(T a, T b) {
        return conversion(a, b, "-");
    }

    private <T,V> double conversion(T a, V b, String operation) {
        double temp=0;
        switch (operation) {
            case "+":temp = Double.valueOf(a.toString()) + new Double(Double.valueOf(b.toString()));
            case "-":temp = Double.valueOf(a.toString()) - new Double(Double.valueOf(b.toString()));
            case "*":temp = Double.valueOf(a.toString()) * new Double(Double.valueOf(b.toString()));
            case "/":temp = Double.valueOf(a.toString()) / new Double(Double.valueOf(b.toString()));
        }
        return temp;
    }
 

}