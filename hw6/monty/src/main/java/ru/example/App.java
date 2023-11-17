package ru.example;

import java.util.HashMap;
import java.util.Map.Entry;
/**
 * Печатает результаты если не менять решение, и если менять
 */
public class App {
     /**     
     * @param iterations - количество попыток
     * @param print {@value false} - только статистика
     * @param print {@value true} - печатать шаги
     */
    public App(int iterations,boolean print) {
        HashMap<Integer, String[]> map = new HashMap<Integer, String[]>();
        MontyGame m = new MontyGame();

        System.out.printf("\nНе изменять выбор %-64s Поменять выбор\n","\t");
       
        for (int i = 0; i < iterations; i++) {
            map.put(i, new String[] { m.MontyGame(1), m.MontyGame(2) });
        }

        if (print) {
            printHead();
        }
        
        int count_prim = 0;
        int count_sec = 0;
    
        for (HashMap.Entry<Integer, String[]> e : map.entrySet()) {
            if (e.getValue()[0].contains("true")) { count_prim++; }
            if (e.getValue()[1].contains("true")) { count_sec++;  }

            if(print){
                printStep(e);
            }            
        }

        System.out.printf("Процент выигрыша %-70d Процент выигрыша %d\n",
                (count_prim * 100) / map.size(), (count_sec * 100) / map.size() );
    }

    private void printStep(Entry<Integer, String[]> e) {
        System.out.printf("%-5d %s%-5d %s\n", e.getKey() + 1, e.getValue()[0],
                e.getKey() + 1, e.getValue()[1]);
    }

    private void printHead() {
        String head = String.format("%-5s|%9s|%12s|%14s| %s|", "шаг", "двери", "перый выбор", "окончательный",
                "результат");
        System.out.println(head + head);
    }

}
