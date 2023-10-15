package hw3;
/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, 
 * если они одинаковые, и false в противном случае. 
 * Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
  */
public class MyCompare {
    public <T> boolean compareArrays(T[] arr1, T[] arr2) {
        return (arr1.getClass()== arr2.getClass()) && (arr1.length == arr2.length);
    }
}
