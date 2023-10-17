package hw3;

/**
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 */
public class Pair<T,V> {
    private T a; 
    private V b;

    public Pair(T a, V b) {
        this.a = a;
        this.b = b;
    }

    public T getFirst() {
        return a;
    }

    public V getSecond() {
        return b;
    }

    @Override
    public String toString() {
        return a.toString() + " " + b.toString();
    }
}
