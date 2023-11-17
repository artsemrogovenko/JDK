package ru.example;

import java.util.Arrays;
import java.util.Random;


public class MontyGame {   

    Random gen = new Random();
    private int[] doors;
    private int choice;
    private int new_door;
    private boolean result;
    public static boolean changeCurrent;

    /**
     * @param mode =1 без смены выбора
     * @param mode =2 поменять первоначальный выбор
     */
    public String MontyGame(int mode) {
        doors = new int[3];// двери
        int shown; // ведущий открывает дверь с козой

        doors[gen.nextInt(3)] = 1;// случайное положение машины
        choice = gen.nextInt(3); // человек хочет открыть эту дверь
        new_door = choice; // дверь для окончательного выбора

        do { // ведущий выберет дверь где нет машины и не была выбрана игроком
            shown = gen.nextInt(3);
        } while (doors[shown] == 1 || shown == choice);

        switch (mode) {
            case 1:
                // System.out.println("текущее решение " + choice);
                checkWin(choice);
                break;
            case 2:
                new_door = freedoor(choice, shown);
                // System.out.println("поменяли решение на " + new_door);
                checkWin(new_door);
                break;
        }
        return String.format("%s %12d|%14d| %-9b ", Arrays.toString(doors), choice+1, new_door+1, result);
    }

    private void even_step(int showman) {
        if (changeCurrent == true) {
            checkWin(freedoor(choice, showman));
            changeCurrent = false;
        }else{
            checkWin(choice);
            changeCurrent = true;
        }
    }

    private void checkWin(int door) {
        if (doors[door] == 1) {
            // System.out.println("выиграли");
            result = true;
            return;
        }
        // System.out.println("проиграли");
        result = false;
    }

    private int freedoor(int player, int showman) {
        int temp;
        do { // выбрать другую дверь
            temp = gen.nextInt(doors.length);
        } while (temp == player || temp == showman);
        return temp;
    }

}
