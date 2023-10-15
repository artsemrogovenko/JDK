package hw3;

/**
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 */
public class Pair<T> {
    private T _a, _b;

    public Pair(T a, T b) {
        _a = a;
        _b = b;
    }

    public T getFirst() {
        return (T) String.valueOf(_a);
    }

    public T getSecond() {
        System.out.println();
        return (T) String.valueOf(_b);
    }

    @Override
    public String toString() {
        return getFirst() + " " + getSecond();
    }
}
