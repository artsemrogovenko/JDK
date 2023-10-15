package hw3;

/** 
 * Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: 
 * sum(), multiply(), divide(), subtract(). 
 * Параметры этих методов – два числа разного типа (но необязательно разного между собой), над которыми должна быть произведена операция.
 */
public class Calculator {

    public <T extends Number> T sum(T  a, T b) {
       return conversion(a, b, "+");
    }

    public <T extends Number> T multiply(T a, T b) {
        return conversion(a, b, "*");
    }

    public <T extends Number> T divide(T a, T b) {
        return conversion(a, b, "/");
    }

    public <T extends Number> T subtract(T a, T b) {
        return conversion(a, b, "-");
    }

    private <T> T conversion(T a, T b, String operation) {
        Double temp=new Double(0);
        switch (operation) {
            case "+":temp = Double.valueOf(a.toString()) + new Double(Double.valueOf(b.toString()));
            case "-":temp = Double.valueOf(a.toString()) - new Double(Double.valueOf(b.toString()));
            case "*":temp = Double.valueOf(a.toString()) * new Double(Double.valueOf(b.toString()));
            case "/":temp = Double.valueOf(a.toString()) / new Double(Double.valueOf(b.toString()));
        }
        if (temp % 1 == 0) {
            return (T) new Integer(temp.intValue());
        }
        return (T) temp;
    }
 

}