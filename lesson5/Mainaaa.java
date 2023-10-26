import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mainaaa {
    private static int count;

    public static void main(String[] args) throws InterruptedException {

        /*
         * Scanner in = new Scanner(System.in);
         * Queue<Task> mystack = new ArrayDeque<>();
         * 
         * ExecutorService executorService = Executors.newFixedThreadPool(2);
         * Thread taskservice = new Thread(() -> {
         * while (true) {
         * try {
         * Thread.sleep(3000);
         * executorService.submit(() -> {
         * Task t = mystack.poll();
         * if (t != null) {
         * System.out.println("Task " + t + " started");
         * t.run();
         * }
         * });
         * } catch (InterruptedException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         * }
         * }); taskservice.start();
         * //executorService.shutdown();
         * 
         * while (in.hasNextLine()) {
         * // 1+2
         * String[] res = in.nextLine().split("\\+");
         * int a = Integer.parseInt(res[0]);
         * int b = Integer.parseInt(res[1]);
         * Task task = new Task(a, b);
         * mystack.add(task);
         * }
         */
        /*
         * Task task = new Task(0);
         * long start =System.currentTimeMillis();
         * CountDownLatch newmcoo = new CountDownLatch(3);
         * task.setCdl(newmcoo);
         * for (int i = 0; i < 3; i++) {
         * Thread trd = new Thread(task);
         * trd.start();
         * }
         * newmcoo.await();
         * 
         * System.out.println(task.getvalue());
         * long end= System.currentTimeMillis();
         * System.out.println("time ="+ (end-start));
         */
        /*
         * Thread tic= new Thread(new TikTak("["));
         * Thread tac= new Thread(new TikTak("]"));
         * Thread toe= new Thread(new TikTak("-"));
         * 
         * tic.start();
         * toe.start();
         * tac.start();
         */
        Queue<Integer> list = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        Runnable r1 = () -> {
            for (int i = 0; i < 100; i++) {
                list.add(i);
            }
        };
        Runnable r2 = () -> {
            for (Integer integer : list) {
                System.out.print(integer+" ");
            }
            System.out.println();
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
