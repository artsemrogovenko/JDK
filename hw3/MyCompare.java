package hw3;
/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, 
 * если они одинаковые, и false в противном случае. 
 * Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
  */
public class MyCompare {
    public <T1,T2> boolean compareArrays(T1[] arr1, T2[] arr2) {
        return (arr1.getClass()== arr2.getClass()) && (arr1.length == arr2.length);
    }
}
